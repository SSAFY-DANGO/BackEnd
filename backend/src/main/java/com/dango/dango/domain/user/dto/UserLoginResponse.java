package com.dango.dango.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {
	private String nickname;
	private String email;

	private String refrigeratorNickname;
	private String accessToken;
	private String refreshToken;
}
