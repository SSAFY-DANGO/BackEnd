package com.dango.dango.domain.user.service;

import com.dango.dango.domain.user.entity.BlackToken;
import com.dango.dango.domain.user.repository.BlackTokenRepository;


public interface BlackTokenService {
	void saveToken(BlackToken blackToken);
	void findByToken(String accessToken);
}
