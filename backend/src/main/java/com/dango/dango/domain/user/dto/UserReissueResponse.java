package com.dango.dango.domain.user.dto;

import lombok.Data;

@Data
public class UserReissueResponse {
	private String refreshToken;
	private String accessToken;
}
