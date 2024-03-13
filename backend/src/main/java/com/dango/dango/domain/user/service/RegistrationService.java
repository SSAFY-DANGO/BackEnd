package com.dango.dango.domain.user.service;

import com.dango.dango.domain.user.dto.UserRegistrationRequest;
import com.dango.dango.domain.user.dto.UserRegistrationResponse;

public interface RegistrationService {
	UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest);
}
