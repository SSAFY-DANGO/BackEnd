package com.dango.dango.domain.kafka.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.dango.dango.domain.detectLog.entity.DetectLog;
import com.dango.dango.domain.detectLog.service.DetectLogService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class kafkaConsumerService {
	private final DetectLogService detectLogService;
	@KafkaListener(topics = "dango-json",groupId = "consumer")
	public void jsonConsumer(String message) throws IOException {
		// JSON을 Map으로 디코딩
		// Gson을 사용하여 JSON 데이터를 맵으로 디코딩
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> data = gson.fromJson(message, type);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSSSSS");

		// 필요한 필드 추출
		String typeValue = (String) data.get("type");
		String deviceValue = (String) data.get("device");
		LocalDateTime timeValue = LocalDateTime.parse((String) data.get("time"),formatter);

		// json 필드의 값도 맵으로 추출
		Map<String, Integer> jsonMap = (Map<String, Integer>) data.get("json");

		// 필요한 필드 출력
		System.out.println("Type: " + typeValue);
		System.out.println("Device: " + deviceValue);
		System.out.println("Time: " + timeValue);

		// json 필드의 값 출력
		System.out.println("Json Data:");
		for (Map.Entry<String, Integer> entry : jsonMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		DetectLog detectLog = DetectLog.builder()
			.inputTime(timeValue)
			.device(deviceValue)
			.inputType(typeValue)
			.data(jsonMap)
			.build();

		detectLogService.saveDetectLog(detectLog);

	}

	@KafkaListener(topics = "dango-door",groupId = "consumer")
	public void doorConsumer(String message) throws IOException {
		log.info(message.toString());
	}
}
