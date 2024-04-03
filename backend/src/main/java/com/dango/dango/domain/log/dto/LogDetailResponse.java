package com.dango.dango.domain.log.dto;

import com.dango.dango.domain.log.entity.Log;
import lombok.Data;

@Data
public class LogDetailResponse {
    public LogDetailResponse(Log log) {
        this.log = log;
    }

    Log log; // id에 해당하는 로그 정보
    // 해당 식재료의 원재료 정보 -> log의 name이랑 ingredientInformation의 name이 같으면 추가
    private boolean isInformationExist; // 식재료에 맞는 원재료 정보 있는지?
    private String type; // 원재료의 타입
    private double calorie; // 100g 당 칼로리
    private double protein; // 100g 당 단백질
    private double sugar; // 100g 당 당류
    private double fat; // 100g 당 지방
    private double carbs; // 100g 당 탄수화물
}
