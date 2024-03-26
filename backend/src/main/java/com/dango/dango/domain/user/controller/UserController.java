package com.dango.dango.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dango.dango.domain.user.dto.UserLoginRequest;
import com.dango.dango.domain.user.dto.UserLoginResponse;
import com.dango.dango.domain.user.dto.UserProfileResponse;
import com.dango.dango.domain.user.dto.UserRegistrationRequest;
import com.dango.dango.domain.user.dto.UserRegistrationResponse;
import com.dango.dango.domain.user.service.AuthService;
import com.dango.dango.domain.user.service.RegistrationService;
import com.dango.dango.domain.user.service.UserService;
import com.dango.dango.global.common.response.CustomResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
	private final RegistrationService registrationService;
	private final AuthService authService;
	private final UserService userService;

	@PostMapping("/login")
	public CustomResponse<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
		UserLoginResponse userLoginResponse = authService.login(userLoginRequest);
		return new CustomResponse<>(HttpStatus.OK.value(),userLoginResponse,"성공적으로 로그인이 되었습니다");
	}

	@PostMapping("/register/profile")
	public CustomResponse<UserRegistrationResponse> register(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest){
		UserRegistrationResponse userRegistrationResponse = registrationService.register(userRegistrationRequest);
		return new CustomResponse<>(HttpStatus.OK.value(),userRegistrationResponse,"성공적으로 회원가입이 되었습니다");
	}

	@GetMapping("/logout")
	public CustomResponse<String> logout(@RequestHeader("Authorization")String accessToken,@RequestHeader("Refresh-Token")String refreshToken){
		authService.logout(accessToken, refreshToken);
		return new CustomResponse<>(HttpStatus.OK.value(),"","logout 성공!");
	}

	@GetMapping("/check")
	public CustomResponse<String> duplicateCheck(
		@RequestParam(value = "email", required = false) String email,
		@RequestParam(value = "nickname", required = false) String nickname) {
		if (email != null) {
			userService.duplicateUsername(email);
			return new CustomResponse<>(HttpStatus.OK.value(), "", "이메일 중복체크 성공");
		} else if (nickname != null) {
			userService.duplicateNickname(nickname);
			return new CustomResponse<>(HttpStatus.OK.value(), "", "닉네임 중복체크 성공");
		} else {
			return new CustomResponse<>(HttpStatus.BAD_REQUEST.value(), "", "올바르지 않은 요청");
		}
	}

	@GetMapping("/profile")
	public CustomResponse<UserProfileResponse> profile(){
		UserProfileResponse userProfileResponse = userService.getProfile();
		return new CustomResponse<>(HttpStatus.OK.value(),userProfileResponse,"유저 정보 조회 성공");
	}

}
