package com.dango.dango.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dango.dango.domain.user.dto.UserLoginRequest;
import com.dango.dango.domain.user.dto.UserLoginResponse;
import com.dango.dango.domain.user.dto.UserRegistrationRequest;
import com.dango.dango.domain.user.dto.UserRegistrationResponse;
import com.dango.dango.domain.user.service.AuthService;
import com.dango.dango.domain.user.service.RegistrationService;
import com.dango.dango.global.common.response.CustomResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	private final RegistrationService registrationService;
	private final AuthService authService;

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
	public CustomResponse<String> logout(){
		authService.logout();
		return new CustomResponse<>(HttpStatus.OK.value(),"","logout 성공!");
	}

}
