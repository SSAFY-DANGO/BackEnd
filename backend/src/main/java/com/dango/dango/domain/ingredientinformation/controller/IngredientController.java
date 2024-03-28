package com.dango.dango.domain.ingredientinformation.controller;

import com.dango.dango.domain.ingredientinformation.entity.IngredientInformation;
import com.dango.dango.domain.ingredientinformation.service.IngredientInformationService;
import com.dango.dango.global.common.response.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/ingredient")
@RestController
public class IngredientController {
    private final IngredientInformationService ingredientInformationService;


    @GetMapping("/search")
    @Operation(summary = "모든 식재료(원재료)의 정보를 반환합니다.", description = "DB기준 모든 원재료를 리스트로 반환합니다.")
    public CustomResponse<List<IngredientInformation>> getAllInformation() {
        List<IngredientInformation> list = ingredientInformationService.findAll();
        return new CustomResponse<>(HttpStatus.OK.value(), list, "모든 식재료 정보 조회 완료");
    }

    @GetMapping("/search/name/{keyword}")
    @Operation(summary = "'이름 기준'으로 특정 식재료(원재료)의 정보를 반환합니다. 포함하면 모두 반환 * like *", description = "전달받은 keyword로 '원재료 이름'을 DB에서 검색하여 그 결과를 리스트로 반환합니다.")
    public CustomResponse<List<IngredientInformation>> getAllInformationByName(@PathVariable String keyword) {
        List<IngredientInformation> list = ingredientInformationService.findIngredientInformationByName(keyword);
        return new CustomResponse<>(HttpStatus.OK.value(), list, keyword + "(이)가 포함된 식재료 정보 조회 완료");
    }

    @GetMapping("/search/type/{keyword}")
    @Operation(summary = "'타입(원재료의 카테고리) 기준'으로 특정 식재료(원재료)의 정보를 반환합니다. 포함하면 모두 반환 * like * ", description = "전달받은 keyword로 '원재료 타입(카테고리)'을 DB에서 검색하여 그 결과를 리스트로 반환합니다.")
    public CustomResponse<List<IngredientInformation>> getAllInformationByType(@PathVariable String keyword) {
        List<IngredientInformation> list = ingredientInformationService.findIngredientInformationByType(keyword);
        return new CustomResponse<>(HttpStatus.OK.value(), list, keyword + "(이)가 포함된 식재료 정보 조회 완료");
    }


}
