package com.dango.dango.domain.door.service;

import java.time.LocalDateTime;

import com.dango.dango.domain.door.entity.Door;

public interface DoorService {
	Door addDoor(Door door);
	LocalDateTime getLatestTime(String deviceNickname, Boolean isOpen);
	Boolean getDoorIsOpen();
}
