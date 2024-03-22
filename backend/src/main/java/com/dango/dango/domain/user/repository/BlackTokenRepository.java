package com.dango.dango.domain.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dango.dango.domain.user.entity.BlackToken;

public interface BlackTokenRepository extends CrudRepository<BlackToken,String> {
	Optional<BlackToken> findByAccessToken(String accessToken);
}
