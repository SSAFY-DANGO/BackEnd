package com.dango.dango.domain.refrigerator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefrigeratorInfoResponse {
    long id;
    String nickname;

}
