package com.dango.dango.domain.kafka.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import com.dango.dango.domain.detectLog.entity.DetectLog;
import com.dango.dango.domain.detectLog.service.DetectLogService;
import com.dango.dango.domain.door.entity.Door;
import com.dango.dango.domain.door.service.DoorService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class kafkaConsumerService {
	private final DetectLogService detectLogService;
	private final DoorService doorService;
	@KafkaListener(topics = "dango-json",groupId = "consumer")
	public void jsonConsumer(String message) throws IOException {
		// JSON을 Map으로 디코딩
		// Gson을 사용하여 JSON 데이터를 맵으로 디코딩
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> data = gson.fromJson(message, type);

		// 필요한 필드 추출
		String typeValue = (String) data.get("type");
		String deviceValue = (String) data.get("device");
		LocalDateTime timeValue = parseLocalDateTime(data.get("time").toString());

		// json 필드의 값도 맵으로 추출
		Map<String, Integer> jsonMap = (Map<String, Integer>) data.get("json");

		DetectLog detectLog = DetectLog.builder()
			.inputTime(timeValue)
			.device(deviceValue)
			.inputType(typeValue)
			.data(jsonMap)
			.build();

		detectLogService.addDetectLog(detectLog);

	}

	@KafkaListener(topics = "dango-door",groupId = "consumer")
	public void doorConsumer(String message,@Header(value = "device")String device,@Header(value = "time") String time) throws IOException {


		Door door = Door
			.builder()
			.deviceNickname(device)
			.isOpen(Boolean.valueOf(message))
			.time(parseLocalDateTime(time))
			.build();

		doorService.addDoor(door);
	}

	public LocalDateTime parseLocalDateTime(String time){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSSSSS");
		return LocalDateTime.parse(time,formatter);
	}
}
