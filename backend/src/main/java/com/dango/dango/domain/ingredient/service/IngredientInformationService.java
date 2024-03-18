package com.dango.dango.domain.ingredient.service;

import com.dango.dango.domain.ingredient.dto.IngredientInformationUpdateRequest;
import com.dango.dango.domain.ingredient.entity.IngredientInformation;

public interface IngredientInformationService {

    // pk로 식재료정보 찾기
    IngredientInformation findIngredientInformationById(Long id);

    // 식재료명으로 조회하기
    IngredientInformation findIngredientInformationByName(String name);

    // 식재료 정보 생성
    IngredientInformation addIngredientInformation(IngredientInformation ingredientInformation);

    // 식재료 정보 삭제
    void deleteIngredientInformation(Long id);

    // 식재료 정보 업데이트
    int updateIngredientInformation(Long id, IngredientInformationUpdateRequest ingredientInformationUpdateRequest);
}
