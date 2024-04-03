package com.dango.dango.domain.user.service;

import com.dango.dango.domain.user.dto.UserLoginRequest;
import com.dango.dango.domain.user.dto.UserLoginResponse;

public interface AuthService {
	UserLoginResponse login(UserLoginRequest userLoginRequest);
	void logout(String accessToken,String refreshToken);
}
