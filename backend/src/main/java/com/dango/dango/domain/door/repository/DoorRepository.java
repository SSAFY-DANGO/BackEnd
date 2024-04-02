package com.dango.dango.domain.door.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dango.dango.domain.door.entity.Door;

public interface DoorRepository extends JpaRepository<Door, Long> {
	@Query(value = "select d from Door d where d.deviceNickname = :deviceNickName and d.isOpen = :isOpen order by d.time desc limit 1")
	Door findLatestItem(@Param("deviceNickname") String deviceNickName, @Param("date") Boolean isOpen);
}