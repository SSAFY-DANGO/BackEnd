package com.dango.dango.domain.door.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dango.dango.domain.door.entity.Door;

public interface DoorRepository extends JpaRepository<Door,Long> {
}
