package com.dango.dango.domain.user.service;

import com.dango.dango.domain.user.dto.UserReissueResponse;

public interface ReissueService {
	UserReissueResponse reissueAccessToken(String refreshToken);
}
