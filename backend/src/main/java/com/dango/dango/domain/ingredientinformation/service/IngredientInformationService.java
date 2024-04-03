package com.dango.dango.domain.ingredientinformation.service;

import com.dango.dango.domain.ingredientinformation.entity.IngredientInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientInformationService {

    // 모든 식재료 정보 반환
    Page<IngredientInformation> findAll(Pageable pageable);

    // pk로 식재료정보 찾기
    IngredientInformation findIngredientInformationById(Long id);

    // 식재료(원재료)명으로 조회하기
    Page<IngredientInformation> findIngredientInformationByName(String name, Pageable pageable);

    IngredientInformation findIngredientInformationByName(String name);

    // 식재료(원재료) 타입으로 조회하기
    Page<IngredientInformation> findIngredientInformationByType(String type, Pageable pageable);

    // 식재료 정보 생성
    IngredientInformation addIngredientInformation(IngredientInformation ingredientInformation);

    // 식재료 정보 삭제
    void deleteIngredientInformation(Long id);

    // 식재료 정보 업데이트
    //    int updateIngredientInformation(Long id, IngredientInformationUpdateRequest ingredientInformationUpdateRequest);
}
