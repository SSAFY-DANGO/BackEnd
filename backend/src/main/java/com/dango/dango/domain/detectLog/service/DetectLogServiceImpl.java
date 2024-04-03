package com.dango.dango.domain.detectLog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dango.dango.domain.detectLog.entity.DetectLog;
import com.dango.dango.domain.detectLog.repository.DetectLogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DetectLogServiceImpl implements DetectLogService {

	private final DetectLogRepository detectLogRepository;

	@Override
	public DetectLog addDetectLog(DetectLog detectLog) {
		return detectLogRepository.save(detectLog);
	}

	@Override
	public DetectLog getLatestItem(String device, String inputType) {
		return detectLogRepository.findAllLatestItem(device, inputType);
	}

	@Override
	public List<String> getAllIngredients(String device, String inputType) {
		return new ArrayList<>(getLatestItem(device, inputType).getData().keySet());
	}
}
