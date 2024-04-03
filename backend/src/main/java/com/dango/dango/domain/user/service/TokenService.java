package com.dango.dango.domain.user.service;

import com.dango.dango.domain.user.entity.Token;

public interface TokenService {
	void saveTokenInfo(Long userId,String refreshToken,String accessToken);
	Token findById(Long userId);
	Token findByRefreshToken(String refreshToken);
	void deleteByRefreshToken(String refreshToken);
	Token findByRefreshTokenAndDelete(String refreshToken);
	void deleteById(Long userId);
}
