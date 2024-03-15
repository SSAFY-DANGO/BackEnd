package com.dango.dango.domain.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dango.dango.domain.user.dto.UserLoginRequest;
import com.dango.dango.domain.user.dto.UserLoginResponse;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.global.common.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserService userService;
	private final RefreshTokenService refreshTokenService;
	private final PasswordEncoder passwordEncoder;

	@Value("${jwt.secret}")
	private String key;

	@Override
	public UserLoginResponse login(UserLoginRequest userLoginRequest) {
		User user = userService.findUserByUsername(userLoginRequest.getUsername());
		// 해당하는 유저가 있는지 먼저 확인한다

		passwordEncoder.matches(user.getPassword(),user.getPassword());
		// 패스워드가 일치하는지 확인한다

		String accessToken = JwtTokenUtil.createToken(user);
		String refreshToken = JwtTokenUtil.createRefreshToken();
		// 토큰 추가

		refreshTokenService.saveTokenInfo(user.getId(),refreshToken,accessToken);
		// refresh token 을 추가한다

		UserLoginResponse userLoginResponse = UserLoginResponse.builder()
			.nickname(user.getNickname())
			.refreshToken(refreshToken)
			.accessToken(accessToken)
			.build();

		return userLoginResponse;
	}
}
