package com.dango.dango.domain.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserProfileResponse {
	private String username;
	private String nickname;
	private LocalDateTime updateTime;
}
