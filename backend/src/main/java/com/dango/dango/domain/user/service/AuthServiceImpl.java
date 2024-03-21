package com.dango.dango.domain.user.service;

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
	private final TokenService tokenService;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenUtil jwtTokenUtil;

	@Override
	public UserLoginResponse login(UserLoginRequest userLoginRequest) {
		User user = userService.findUserByUsername(userLoginRequest.getUsername());
		// 해당하는 유저가 있는지 먼저 확인한다

		passwordEncoder.matches(user.getPassword(),user.getPassword());
		// 패스워드가 일치하는지 확인한다

		String accessToken = jwtTokenUtil.createToken(user);
		String refreshToken = jwtTokenUtil.createRefreshToken();
		// 토큰 추가

		tokenService.saveTokenInfo(user.getId(),refreshToken,accessToken);
		// refresh token 을 추가한다

		UserLoginResponse userLoginResponse = UserLoginResponse.builder()
			.nickname(user.getNickname())
			.refreshToken(refreshToken)
			.accessToken(accessToken)
			.build();

		return userLoginResponse;
	}

	@Override
	public void logout() {

		User user = userService.findUserByToken();
		// 존재하는 유저인지 검증

		tokenService.deleteById(user.getId());
		// 해당 유저의 토큰이 존재하는지 확인 후 삭제

		// 로그아웃하고나서는 리프레쉬 토큰 발급이 되지않도록 해야함
	}
}
