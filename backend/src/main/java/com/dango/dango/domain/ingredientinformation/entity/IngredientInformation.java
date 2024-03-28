package com.dango.dango.domain.ingredient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class IngredientInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String type;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    @CreationTimestamp
    private LocalDateTime createTime;

    private double calorie;

    private double protein;

    private double sugar;

    private double fat;

    private double carbs;

    @ColumnDefault("100")
    private int amount;

}
