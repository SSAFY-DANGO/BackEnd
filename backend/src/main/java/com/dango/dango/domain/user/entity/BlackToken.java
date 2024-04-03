package com.dango.dango.domain.user.entity;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@RedisHash(value = "blackToken")
public class BlackToken {

	@Id
	private String id;

	@Indexed
	private String accessToken;
	private String value;

	@TimeToLive
	private Long timeToLive;
	// 원래 액세스 토큰이 만료될때까지
}
