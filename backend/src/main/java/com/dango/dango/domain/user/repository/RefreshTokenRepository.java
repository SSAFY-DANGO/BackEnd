package com.dango.dango.domain.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dango.dango.domain.user.entity.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken,String> {
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
