package com.dango.dango.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dango.dango.domain.user.dto.UserReissueResponse;
import com.dango.dango.domain.user.service.RefreshTokenService;
import com.dango.dango.domain.user.service.ReissueService;
import com.dango.dango.global.common.response.CustomResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class TokenController {
	private final ReissueService reissueService;
	@GetMapping("/reissue")
	public CustomResponse<UserReissueResponse> refreshToken(@RequestHeader("Refresh-Token")String refreshToken){
		UserReissueResponse userReissueResponse = reissueService.reissueAccessToken(refreshToken);
		return new CustomResponse<>(HttpStatus.ACCEPTED.value(),userReissueResponse,"access token이 재발급되었습니다");
	}
}
