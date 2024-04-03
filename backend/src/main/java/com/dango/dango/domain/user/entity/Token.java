package com.dango.dango.domain.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@RedisHash(value = "refreshToken",timeToLive = 1000L * 60 * 60 * 24 * 7)
public class Token {
	@Id
	private String userId;

	@Indexed
	private String refreshToken;

	private String accessToken;
	//이 필드값으로 데이터를 찾아와야 하기 때문
	//만료된 accessToken으로 refresh Token을 찾아와서 유효성을 검사

}
