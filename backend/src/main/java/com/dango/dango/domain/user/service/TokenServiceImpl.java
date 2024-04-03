package com.dango.dango.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dango.dango.domain.user.entity.Token;
import com.dango.dango.domain.user.repository.RefreshTokenRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	@Transactional
	public void saveTokenInfo(Long userId, String refreshToken, String accessToken) {
		refreshTokenRepository.save(
			Token.builder()
				.userId(String.valueOf(userId))
				.refreshToken(refreshToken)
				.accessToken(accessToken)
				.build()
		);
	}

	@Override
	public Token findById(Long userId) {
		return refreshTokenRepository.findById(String.valueOf(userId)).orElseThrow(
			() -> new EntityNotFoundException("해당하는 토큰이 존재하지 않습니다")
		);
	}
	// 토큰 저장

	@Override
	public Token findByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken)
			.orElseThrow(
				()-> new EntityNotFoundException(
			"해당하는 토큰이 없습니다"
		));
	}
	// 리프레시 토큰을 기준으로 토큰을 찾습니다

	@Override
	public void deleteByRefreshToken(String refreshToken){
		refreshTokenRepository.findByRefreshToken(refreshToken)
			.ifPresent(
				token -> refreshTokenRepository.delete(token)
			);
	}

	@Override
	public Token findByRefreshTokenAndDelete(String refreshToken) {
		Token token = findByRefreshToken(refreshToken);
		deleteByRefreshToken(refreshToken);
		return token;
	}

	@Override
	public void deleteById(Long userId) {
		findById(userId); // 토큰이 없을 경우 토큰을 찾을때 throw
		refreshTokenRepository.deleteById(String.valueOf(userId));
	}

}
