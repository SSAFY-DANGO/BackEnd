package com.dango.dango.domain.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dango.dango.domain.user.dto.UserReissueResponse;
import com.dango.dango.domain.user.entity.RefreshToken;

@Mapper
public interface TokenMapper {
	TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);
	UserReissueResponse RefreshTokenToUserReissue(RefreshToken refreshToken);
}
