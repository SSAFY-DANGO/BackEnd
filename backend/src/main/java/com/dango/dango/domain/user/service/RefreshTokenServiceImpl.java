package com.dango.dango.domain.user.service;

import java.sql.Ref;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dango.dango.domain.user.entity.RefreshToken;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.repository.RefreshTokenRepository;
import com.dango.dango.global.common.util.JwtTokenUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService{

	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	@Transactional
	public void saveTokenInfo(Long userId, String refreshToken, String accessToken) {
		refreshTokenRepository.save(
			RefreshToken.builder()
				.userId(String.valueOf(userId))
				.refreshToken(refreshToken)
				.accessToken(accessToken)
				.build()
		);
	}
	// 토큰 저장

	@Override
	public RefreshToken findByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new EntityNotFoundException(
			"해당하는 토큰이 없습니다"
		));
	}
	// 리프레시 토큰을 기준으로 토큰을 찾습니다


}
