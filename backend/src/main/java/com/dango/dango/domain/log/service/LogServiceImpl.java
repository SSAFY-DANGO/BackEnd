package com.dango.dango.domain.log.service;

import com.dango.dango.domain.ingredientinformation.entity.IngredientInformation;
import com.dango.dango.domain.ingredientinformation.repository.IngredientInformationRepository;
import com.dango.dango.domain.log.dto.LogDetailResponse;
import com.dango.dango.domain.log.dto.LogEditRequest;
import com.dango.dango.domain.log.dto.LogRegisterRequest;
import com.dango.dango.domain.log.entity.Log;
import com.dango.dango.domain.log.exception.LogNotFoundException;
import com.dango.dango.domain.log.repository.LogQueryRepository;
import com.dango.dango.domain.log.repository.LogRepository;
import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotFoundException;
import com.dango.dango.domain.refrigerator.repository.RefrigeratorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final IngredientInformationRepository ingredientInformationRepository;
    private final LogQueryRepository logQueryRepository;

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
                .exist(true)
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
    public LogDetailResponse getLogDetailById(Long id) {
        Log log = logRepository.findById(id)
                .orElseThrow(() -> new LogNotFoundException(id + " 번 식재료 로그 없음"));
        // null이면 dto에 내용 추가 x
        IngredientInformation ingredientInformation = ingredientInformationRepository.findByName(log.getName())
                .orElse(null);

        LogDetailResponse res = new LogDetailResponse(log);
        if (ingredientInformation != null) {
            res.setInformationExist(true);
            res.setType(ingredientInformation.getType());
            res.setCalorie(ingredientInformation.getCalorie());
            res.setProtein(ingredientInformation.getProtein());
            res.setSugar(ingredientInformation.getSugar());
            res.setFat(ingredientInformation.getFat());
            res.setCarbs(ingredientInformation.getCarbs());
        }
        return res;
    }

    @Override
    @Transactional
    public void deleteLog(Long id) {
        Log log = logRepository.findById(id)
                .orElseThrow(() -> new LogNotFoundException(id + " 번 식재료 로그 없음"));
        // logRepository.deleteById(id);
        log.setExist(false);
        log.setDeleteTime(LocalDateTime.now());
    }

    @Override
    public List<Log> getItems(String device, Boolean exist) {
        return logQueryRepository.findAllByRefrigeratorNickname(device, exist);
    }
}
