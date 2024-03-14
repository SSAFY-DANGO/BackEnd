package com.dango.dango.domain.ingredient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class IngredientInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String type;

    @UpdateTimestamp
    @CreationTimestamp
    private LocalDateTime updateTime;

    @CreationTimestamp
    private LocalDateTime createTime;

    private double calorie;

    private double protein;

    private double sugar;

    private double fat;

    private double carbs;

    private int amount;

}
