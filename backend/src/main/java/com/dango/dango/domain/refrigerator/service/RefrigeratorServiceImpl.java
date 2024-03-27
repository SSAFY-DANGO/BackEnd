package com.dango.dango.domain.refrigerator.service;

import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.repository.LogRepository;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorDuplicatedException;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotFoundException;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotMatchException;
import com.dango.dango.domain.refrigerator.repository.RefrigeratorRepository;
import com.dango.dango.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RefrigeratorServiceImpl implements RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;
    private final LogRepository logRepository;

    @Override
    @Transactional
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
    public Refrigerator registerRefrigerator(String nickname) {
        Refrigerator refrigerator = Refrigerator.builder()
                .nickname(nickname).build();
        refrigeratorRepository.save(refrigerator);
        return refrigerator;
    }

    @Override
    @Transactional
    public Refrigerator editRefrigerator(User user, String nickname) {
        Refrigerator refrigerator = registerRefrigerator(nickname);
        user.setRefrigeratorId(refrigerator.getId());
        return refrigerator;
    }

    @Override
    @Transactional
    public void deleteRefrigerator(Long refrigeratorId) {
        refrigeratorRepository.deleteById(refrigeratorId);
    }


    @Override
    public List<Log> getItems(Long refrigeratorId, Long userRefrigeratorId) {
        if (refrigeratorId != userRefrigeratorId) {
            throw new RefrigeratorNotMatchException("자신의 냉장고가 아닙니다.");
        }
        return logRepository.findAllByRefrigeratorId(refrigeratorId);
    }
}
