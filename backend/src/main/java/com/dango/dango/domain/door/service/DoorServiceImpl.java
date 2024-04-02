package com.dango.dango.domain.door.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dango.dango.domain.door.entity.Door;
import com.dango.dango.domain.door.repository.DoorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoorServiceImpl implements DoorService {
	private final DoorRepository doorRepository;

	@Override
	public Door addDoor(Door door) {
		return doorRepository.save(door);
	}

	@Override
	public LocalDateTime getLatestTime(String deviceNickname, Boolean isOpen) {
		return doorRepository.findLatestItem(deviceNickname, isOpen).getTime();
	}
}
