package com.dango.dango.domain.log.service;

import java.util.List;

import com.dango.dango.domain.log.dto.LogDetailResponse;
import com.dango.dango.domain.log.dto.LogEditRequest;
import com.dango.dango.domain.log.dto.LogRegisterRequest;
import com.dango.dango.domain.log.entity.Log;

public interface LogService {

	Log findById(Long id);

	Log registerLog(LogRegisterRequest logRegisterRequest);

	Log editLog(LogEditRequest logEditRequest);

	LogDetailResponse getLogDetailById(Long id);

	void deleteLog(Long id);

	List<Log> getItems(String device, Boolean exist);
}
