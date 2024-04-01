package com.dango.dango.domain.detectLog.service;

import org.springframework.stereotype.Service;

import com.dango.dango.domain.detectLog.entity.DetectLog;
import com.dango.dango.domain.detectLog.repository.DetectLogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DetectLogServiceImpl implements DetectLogService{

	private final DetectLogRepository detectLogRepository;

	@Override
	public DetectLog saveDetectLog(DetectLog detectLog) {
		return detectLogRepository.save(detectLog);
	}
}
