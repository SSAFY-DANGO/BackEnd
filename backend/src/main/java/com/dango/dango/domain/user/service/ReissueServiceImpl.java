package com.dango.dango.domain.user.service;

import org.springframework.stereotype.Service;

import com.dango.dango.domain.user.dto.UserReissueResponse;
import com.dango.dango.domain.user.entity.Token;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.mapper.TokenMapper;
import com.dango.dango.global.common.util.JwtTokenUtil;

import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReissueServiceImpl implements ReissueService{

	private final TokenService refreshTokenService;
	private final UserService userService;
	private final JwtTokenUtil jwtTokenUtil;

	@Override
	public UserReissueResponse reissueAccessToken(String refreshToken) throws SignatureException {

		Token token = refreshTokenService.findByRefreshTokenAndDelete(refreshToken);
		// redis에 저장된 정보를 가져온 다음

		String username = jwtTokenUtil.extractUsername(token.getAccessToken());
		// 해당 리프레쉬 토큰의 유저를 가져온다

		User user = userService.findUserByUsername(username);
		// 해당 유저 정보를 가져온다
		// 해당 유저가 있는지 확인을 한번 더 하기 위해 조회한다

		String accessToken = jwtTokenUtil.createToken(user);
		// accessToken 을 새로 발급한다

		String newRefreshToken = jwtTokenUtil.createRefreshToken();

		refreshTokenService.saveTokenInfo(user.getId(),refreshToken,accessToken);

		UserReissueResponse newToken = TokenMapper.INSTANCE.RefreshTokenToUserReissue(
			Token.builder()
			.accessToken(accessToken)
			.refreshToken(newRefreshToken)
			.userId(String.valueOf(user.getId()))
			.build()
		);
		return newToken;
	}
}
