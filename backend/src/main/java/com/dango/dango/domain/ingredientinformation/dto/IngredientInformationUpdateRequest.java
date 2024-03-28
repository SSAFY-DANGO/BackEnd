package com.dango.dango.domain.ingredient.dto;

import lombok.Data;

@Data
public class IngredientInformationUpdateRequest {
    private String name;
    private String type;
    private Double calorie;
    private Double protein;
    private Double sugar;
    private Double fat;
    private Double carbs;
    private Integer amount;
}
