package com.dango.dango.domain.user.service;

import com.dango.dango.domain.user.entity.RefreshToken;

public interface RefreshTokenService {
	void saveTokenInfo(Long userId,String refreshToken,String accessToken);
	RefreshToken findByRefreshToken(String refreshToken);

}
