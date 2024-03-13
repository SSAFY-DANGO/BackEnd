package com.dango.dango.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dango.dango.domain.user.dto.UserRegistrationRequest;
import com.dango.dango.domain.user.dto.UserRegistrationResponse;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) {

		User user = UserMapper.INSTANCE.UserRegistrationToUser(userRegistrationRequest);
		// 유저 정보를 입력받음

		userService.duplicateNickname(user.getNickname());
		userService.duplicateUsername(user.getUsername());
		//중복확인

		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		// 비밀번호 암호화

		UserRegistrationResponse userRegistrationResponse = UserMapper.INSTANCE.UserToUserRegistration(userService.addUser(user));

		return userRegistrationResponse;

	}
}
