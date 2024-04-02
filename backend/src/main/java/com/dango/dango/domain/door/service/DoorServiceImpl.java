package com.dango.dango.domain.door.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dango.dango.domain.door.entity.Door;
import com.dango.dango.domain.door.repository.DoorRepository;
import com.dango.dango.domain.refrigerator.dto.RefrigeratorInfoResponse;
import com.dango.dango.domain.refrigerator.service.RefrigeratorService;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoorServiceImpl implements DoorService {
	private final DoorRepository doorRepository;
	private final RefrigeratorService refrigeratorService;

	@Override
	public Door addDoor(Door door) {
		return doorRepository.save(door);
	}

	@Override
	public LocalDateTime getLatestTime(String deviceNickname, Boolean isOpen) {
		return doorRepository.findLatestItem(deviceNickname, isOpen).getTime();
	}

	@Override
	public Boolean getDoorIsOpen() {
		RefrigeratorInfoResponse refrigeratorInfoResponse = refrigeratorService.getRefrigerator();
		String refrigeratorNickname = refrigeratorInfoResponse.getNickname();
		Boolean isOpen = doorRepository.findLatestItemByNickname(refrigeratorNickname);
		return isOpen;
	}
}
