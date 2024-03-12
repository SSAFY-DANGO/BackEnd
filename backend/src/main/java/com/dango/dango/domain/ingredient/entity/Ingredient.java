package com.dango.dango.domain.ingredient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(name = "refrigerator_id", nullable = false)
    @JoinColumn(name = "id")
    private Refrigerator refrigeratorId;

    @NotNull
    private String name;
    @NotNull
    private String category;

    @NotNull
    @Column(name = "expiration_time")
    @CreationTimestamp // 이후 수정 필요 -> 소비기한
    private LocalDateTime expirationTime;

    @NotNull
    @Column(name = "update_time")
    @CreationTimestamp
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @NotNull
    @Column(name = "create_time")
    @CreationTimestamp
    private LocalDateTime createTime;

    @NotNull
    @Column(name = "input_time")
    @CreationTimestamp
    private LocalDateTime inputTime;

    @Column(name = "output_time")
    private LocalDateTime outputTime;

    private int type;
    private boolean exist;

}
