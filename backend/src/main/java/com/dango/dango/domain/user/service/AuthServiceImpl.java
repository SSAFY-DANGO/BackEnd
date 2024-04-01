package com.dango.dango.domain.user.service;

import static java.rmi.server.LogStream.*;

import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dango.dango.domain.refrigerator.exception.RefrigeratorNotFoundException;
import com.dango.dango.domain.refrigerator.service.RefrigeratorService;
import com.dango.dango.domain.user.dto.UserLoginRequest;
import com.dango.dango.domain.user.dto.UserLoginResponse;
import com.dango.dango.domain.user.entity.BlackToken;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.global.common.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserService userService;
	private final TokenService tokenService;
	private final BlackTokenService blackTokenService;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenUtil jwtTokenUtil;
	private final RefrigeratorService refrigeratorService;

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

		String refrigeratorNickname = "";
		Long refrigeratorId = user.getRefrigeratorId();
		if(refrigeratorId != null){
			refrigeratorNickname = refrigeratorService.findRefrigeratorById(refrigeratorId).getNickname();
		}

		UserLoginResponse userLoginResponse = UserLoginResponse.builder()
			.nickname(user.getNickname())
			.refrigeratorNickname(refrigeratorNickname)
			.refreshToken(refreshToken)
			.accessToken(accessToken)
			.build();

		return userLoginResponse;
	}

	@Override
	public void logout(String accessToken,String refreshToken) {

		String extractedAccessToken = jwtTokenUtil.extractToken(accessToken);
		String extractedRefreshToken = jwtTokenUtil.extractToken(refreshToken);

		jwtTokenUtil.validateToken(extractedAccessToken);
		jwtTokenUtil.validateToken(extractedRefreshToken);

		tokenService.findByRefreshToken(extractedRefreshToken);
		// 해당 유저의 토큰이 존재하는지 확인 후 삭제

		Date expiredTime = jwtTokenUtil.extractTime(extractedAccessToken);
		Long timeToLive = expiredTime.getTime() - System.currentTimeMillis();
		// 만료기간 설정

		BlackToken blackToken = BlackToken.builder().accessToken(extractedAccessToken).value("logout").timeToLive(timeToLive).build();
		blackTokenService.saveToken(blackToken);
		// 블랙 토큰 생성

	}
}
