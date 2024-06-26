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

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RefrigeratorServiceImpl implements RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;
    private final UserRepository userRepository;
    private final LogRepository logRepository;
    private final UserService userService;

    @Override
    public Refrigerator findRefrigeratorByNickname(String refrigeratorNickname) {
        Refrigerator res = refrigeratorRepository.findByNickname(refrigeratorNickname)
                .orElseThrow(
                        () -> new RefrigeratorNotFoundException(refrigeratorNickname + " 냉장고가 없습니다."));
        return res;
    }

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
        // 닉네임에 해당하는 냉장고가 있는지 찾아보고
        Refrigerator refrigerator = refrigeratorRepository.findByNickname(nickname).orElse(null);
        // 없으면 새로만들기
        if (refrigerator == null) {
            refrigerator = Refrigerator.builder()
                    .nickname(nickname).build();
            refrigeratorRepository.save(refrigerator);
        }
        // 이 유저에게 냉장고가 속하게 설정
        user.setRefrigeratorId(refrigerator.getId());
        userRepository.save(user);

        RefrigeratorInfoResponse res = RefrigeratorInfoResponse.builder()
                .id(refrigerator.getId())
                .nickname(refrigerator.getNickname())
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
        // 닉네임으로 냉장고를 찾고, 없으면 새로 만들기
        Refrigerator refrigerator = refrigeratorRepository.findByNickname(nickname).orElse(null);
        if (refrigerator == null) {
            refrigerator = Refrigerator.builder()
                    .nickname(nickname).build();
            refrigeratorRepository.save(refrigerator);
        }
        user.setRefrigeratorId(refrigerator.getId());
        userRepository.save(user);

        RefrigeratorInfoResponse res = RefrigeratorInfoResponse.builder()
                .id(refrigerator.getId())
                .nickname(refrigerator.getNickname())
                .build();
        return res;
    }

    @Override
    @Transactional
    public Long deleteRefrigerator() {
        // 토큰으로 유저를 찾고
        User user = userService.findUserByToken();
        // 유저에게 냉장고 있는지 확인
        Long refrigeratorId = user.getRefrigeratorId();
        throwIfRefrigeratorNotExist(refrigeratorId);
        // 유저의 냉장고 정보만 삭제
        user.setRefrigeratorId(null);
        userRepository.save(user);
        return refrigeratorId;
    }


    @Override
    public List<Log> getItems(String refrigeratorNickname) {
        User user = userService.findUserByToken();
        Refrigerator refrigerator = findRefrigeratorByNickname(refrigeratorNickname);
        // 요청으로 넘어온 냉장고 닉네임과 실제 유저의 냉장고 정보가 다르면
        if (refrigerator.getId().longValue() != user.getRefrigeratorId()) {
            throw new RefrigeratorNotMatchException("자신의 냉장고가 아닙니다.");
        }
        return logRepository.findAllByRefrigeratorId(refrigerator.getId(), true);
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

    @Override
    public List<Log> getOldItems(String refrigeratorNickname, Long day) {
        // 지금날짜부터 day만큼 이전 날짜를 기준으로 db에서 검색
        LocalDateTime date = LocalDateTime.now().minusDays(day);
        User user = userService.findUserByToken();
        Refrigerator refrigerator = findRefrigeratorById(user.getRefrigeratorId());
        List<Log> list = logRepository.findAllOldItems(refrigerator.getId(), date);
        return list;
    }

    @Override
    public Boolean getRefrigeratorDoor(String refrigeratorNickname) {
        return findRefrigeratorByNickname(refrigeratorNickname).is_open();
    }

}
