package com.dango.dango.domain.ingredientinformation.service;

import com.dango.dango.domain.ingredientinformation.entity.IngredientInformation;

import java.util.List;

public interface IngredientInformationService {

    // 모든 식재료 정보 반환
    List<IngredientInformation> findAll();

    // pk로 식재료정보 찾기
    IngredientInformation findIngredientInformationById(Long id);

    // 식재료(원재료)명으로 조회하기
    List<IngredientInformation> findIngredientInformationByName(String name);

    // 식재료(원재료) 타입으로 조회하기
    List<IngredientInformation> findIngredientInformationByType(String type);

    // 식재료 정보 생성
    IngredientInformation addIngredientInformation(IngredientInformation ingredientInformation);

    // 식재료 정보 삭제
    void deleteIngredientInformation(Long id);

    // 식재료 정보 업데이트
//    int updateIngredientInformation(Long id, IngredientInformationUpdateRequest ingredientInformationUpdateRequest);
}
