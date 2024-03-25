package com.dango.dango.domain.log.service;

import com.dango.dango.domain.log.dto.LogEditRequest;
import com.dango.dango.domain.log.dto.LogRegisterRequest;
import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.exception.LogNotFoundException;
import com.dango.dango.domain.log.repository.LogRepository;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotFoundException;
import com.dango.dango.domain.refrigerator.repository.RefrigeratorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final RefrigeratorRepository refrigeratorRepository;

    @Override
    public Log findById(Long id) {

        return logRepository.findById(id)
                .orElseThrow(() -> new LogNotFoundException(id + "번 정보가 없습니다."));
    }

    @Override
    @Transactional
    public Log registerLog(LogRegisterRequest logRegisterRequest) {
        Long refrigeratorId = logRegisterRequest.getRefrigeratorId();
        Refrigerator refrigerator = refrigeratorRepository.findById(refrigeratorId)
                .orElseThrow(() -> new RefrigeratorNotFoundException(refrigeratorId + "번 냉장고 없음"));
        Log log = Log.builder()
                .refrigerator(refrigerator)
                .name(logRegisterRequest.getName())
                .category(logRegisterRequest.getCategory())
                .type(logRegisterRequest.getType())
                .build();
        logRepository.save(log);
        return log;
    }

    @Override
    @Transactional
    public Log editLog(LogEditRequest logEditRequest) {
        Long logId = logEditRequest.getId();
        Log log = logRepository.findById(logId)
                .orElseThrow(() -> new LogNotFoundException(logId + "번 식재료 로그 없음"));
        // 업데이트
        if (logEditRequest.getName() != null) {
            log.setName(logEditRequest.getName());
        }
        if (logEditRequest.getCategory() != null) {
            log.setCategory(logEditRequest.getCategory());
        }
        if (logEditRequest.getExpirationTime() != null) {
            log.setExpirationTime(logEditRequest.getExpirationTime());
        }
        if (logEditRequest.getType() != null) {
            log.setType(logEditRequest.getType());
        }
        if (logEditRequest.getExist() != null) {
            log.setExist(logEditRequest.getExist());
        }
        return log;
    }

    @Override
    public void deleteLog(Long id) {
        Log log = logRepository.findById(id)
                .orElseThrow(() -> new LogNotFoundException(id + " 번 식재료 로그 없음"));
        logRepository.deleteById(id);
    }
}
