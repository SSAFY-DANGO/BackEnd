package com.dango.dango.domain.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dango.dango.domain.user.dto.UserRegistrationRequest;
import com.dango.dango.domain.user.dto.UserRegistrationResponse;
import com.dango.dango.domain.user.entity.User;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	User UserRegistrationToUser(UserRegistrationRequest userRegistration);
	UserRegistrationResponse UserToUserRegistration(User user);
}
