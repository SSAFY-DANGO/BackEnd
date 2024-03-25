package com.dango.dango.domain.log.entity;

import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "refrigeratorId")
    private Refrigerator refrigerator;

    private String name;
    private String category;
    private LocalDateTime expirationTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;
    @CreationTimestamp
    private LocalDateTime createTime;
    @CreationTimestamp
    private LocalDateTime inputTime;
    private LocalDateTime outputTime;
    private int type;
    private boolean exist;




}
