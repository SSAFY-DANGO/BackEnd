package com.dango.dango.domain.log.service;

import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.exception.LogNotFoundException;
import com.dango.dango.domain.log.repository.LogRepository;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotFoundException;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotMatchException;
import com.dango.dango.domain.refrigerator.repository.RefrigeratorRepository;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrashServiceImpl implements TrashService {
    private final LogRepository logRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final UserService userService;

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
        return logRepository.findAllByRefrigeratorId(refrigerator.getId(), false);
    }

    @Override
    @Transactional
    public Log restoreLog(Long id) {
        Log log = logRepository.findById(id)
                .orElseThrow(() -> new LogNotFoundException(id + " 번 식재료 로그 없음"));
        log.setExist(true);
        log.setDeleteTime(null);
        return log;
    }
}
