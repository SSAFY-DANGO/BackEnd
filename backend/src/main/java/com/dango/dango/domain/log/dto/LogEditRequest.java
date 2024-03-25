package com.dango.dango.domain.log.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LogEditRequest {
    @NotNull
    private Long id;
    private String name;
    private String category;
    private LocalDateTime expirationTime;
    private LocalDateTime inputTime;
    private LocalDateTime outputTime;
    private Integer type;
    private Boolean exist;


}
