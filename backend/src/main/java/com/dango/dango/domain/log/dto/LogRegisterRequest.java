package com.dango.dango.domain.log.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LogRegisterRequest {
    @NotBlank
    private Long refrigeratorId;
    @NotBlank
    private String name;
    @NotBlank
    private String category;
    // 입력형태 -? IoT에서 오는지 사용자가 직접 입력했는지?
    @NotBlank
    private int type;


}
