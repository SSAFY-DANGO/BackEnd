package com.dango.dango.domain.ingredient.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor

public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Column(name = "refrigerator_id")
    @JoinColumn(name ="id" )
    private Refrigerator refrigeratorId;
    private String name;
    private String category;
    private LocalDateTime expirationTime;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private LocalDateTime inputTime;
    private LocalDateTime outputTime;
    private int type;
    private boolean exist;

}
