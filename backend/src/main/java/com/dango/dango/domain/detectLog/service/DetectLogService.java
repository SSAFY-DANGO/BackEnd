package com.dango.dango.domain.detectLog.service;

import java.util.List;

import com.dango.dango.domain.detectLog.entity.DetectLog;

public interface DetectLogService {
	DetectLog addDetectLog(DetectLog detectLog);

	DetectLog getLatestItem(String device, String inputType);

	List<String> getAllIngredients(String device, String inputType);
}
