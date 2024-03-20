package com.dango.dango.domain.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dango.dango.domain.user.entity.Token;

public interface RefreshTokenRepository extends CrudRepository<Token,String> {
	Optional<Token> findByRefreshToken(String refreshToken);
}
