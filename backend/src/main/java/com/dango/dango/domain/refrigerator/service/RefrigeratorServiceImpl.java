package com.dango.dango.domain.refrigerator.service;

import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.repository.LogRepository;
import com.dango.dango.domain.refrigerator.dto.RefrigeratorInfoResponse;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorDuplicatedException;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotFoundException;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotMatchException;
import com.dango.dango.domain.refrigerator.repository.RefrigeratorRepository;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.repository.UserRepository;
import com.dango.dango.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RefrigeratorServiceImpl implements RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;
    private final UserRepository userRepository;
    private final LogRepository logRepository;
    private final UserService userService;

    @Override
    public Refrigerator findRefrigeratorById(Long id) {

        Refrigerator res = refrigeratorRepository.findById(id).orElseThrow(
                () -> new RefrigeratorNotFoundException("냉장고가 없습니다."));
        return res;
    }

    @Override
    public boolean isRefrigeratorExist(Long id) {
        // id에 해당하는 냉장고 있는지?
        return refrigeratorRepository.findById(id).isPresent();
    }

    @Override
    public void throwIfRefrigeratorDuplicated(Long id) {
        if (id == null) {
            return;
        }
        refrigeratorRepository.findById(id).ifPresent(
                (res) -> {
                    throw new RefrigeratorDuplicatedException(res.getNickname() + " 냉장고가 이미 있습니다.");
                }
        );

    }

    @Override
    public void throwIfRefrigeratorNotExist(Long id) {
        if (refrigeratorRepository.findById(id).isEmpty()) {
            throw new RefrigeratorNotFoundException("등록된 냉장고가 없습니다.");
        }
    }

    @Override
    @Transactional
    public RefrigeratorInfoResponse registerRefrigerator(String nickname) {
        User user = userService.findUserByToken();
        // 이미 등록된 냉장고가 있으면 불가능
        throwIfRefrigeratorDuplicated(user.getRefrigeratorId());

        Refrigerator refrigerator = Refrigerator.builder()
                .nickname(nickname).build();
        Refrigerator saved = refrigeratorRepository.save(refrigerator);
        // 이 유저에게 냉장고가 속하게 설정
        user.setRefrigeratorId(saved.getId());
        userRepository.save(user);

        RefrigeratorInfoResponse res = RefrigeratorInfoResponse.builder()
                .id(saved.getId())
                .nickname(saved.getNickname())
                .build();

        return res;
    }

    @Override
    @Transactional
    public RefrigeratorInfoResponse editRefrigerator(String nickname) {
        User user = userService.findUserByToken();
        // 이미 등록된 냉장고가 있어야 수정이 가능함
        if (user.getRefrigeratorId() == null) {
            throw new RefrigeratorNotFoundException("등록된 냉장고가 없습니다.");
        }
        // 새로 저장할 냉장고 만들고 DB에 저장
        Refrigerator refrigerator = Refrigerator.builder()
                .nickname(nickname).build();

        Refrigerator saved = refrigeratorRepository.save(refrigerator);
        user.setRefrigeratorId(saved.getId());
        userRepository.save(user);

        RefrigeratorInfoResponse res = RefrigeratorInfoResponse.builder()
                .id(saved.getId())
                .nickname(saved.getNickname())
                .build();
        return res;
    }

    @Override
    @Transactional
    public Long deleteRefrigerator() {
        User user = userService.findUserByToken();
        Long refrigeratorId = user.getRefrigeratorId();
        throwIfRefrigeratorNotExist(refrigeratorId);
        refrigeratorRepository.deleteById(refrigeratorId);
        user.setRefrigeratorId(null);
        userRepository.save(user);
        return refrigeratorId;
    }


    @Override
    public List<Log> getItems(String refrigeratorNickname) {
        User user = userService.findUserByToken();
        Refrigerator refrigerator = refrigeratorRepository.findByNickname(refrigeratorNickname)
                .orElseThrow(
                        () -> new RefrigeratorNotFoundException(refrigeratorNickname + " 냉장고가 없습니다."));
        // 요청으로 넘어온 냉장고 닉네임과 실제 유저의 냉장고 정보가 다르면
        if (refrigerator.getId().longValue() != user.getRefrigeratorId()) {
            throw new RefrigeratorNotMatchException("자신의 냉장고가 아닙니다.");
        }
        return logRepository.findAllByRefrigeratorId(refrigerator.getId());
    }

    @Override
    public RefrigeratorInfoResponse getRefrigerator() {
        // 토큰으로 유저 찾고
        User user = userService.findUserByToken();
        // 유저의 냉장고 조회
        Refrigerator refrigerator = findRefrigeratorById(user.getRefrigeratorId());
        // 냉장고의 닉네임, 아이디만 응답으로 보내기
        RefrigeratorInfoResponse res = RefrigeratorInfoResponse.builder()
                .id(refrigerator.getId())
                .nickname(refrigerator.getNickname())
                .build();
        return res;
    }
}
