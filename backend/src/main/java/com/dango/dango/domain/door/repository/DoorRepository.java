package com.dango.dango.domain.door.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dango.dango.domain.door.entity.Door;

@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {
	@Query(value = "select d from Door d where d.deviceNickname = :deviceNickname and d.isOpen = :isOpen order by d.time desc limit 1")
	Door findLatestItem(@Param("deviceNickname") String deviceNickname, @Param("isOpen") Boolean isOpen);

	@Query(value = "select d.isOpen from Door d where d.deviceNickname = :deviceNickname order by d.time desc limit 1")
	Boolean findLatestItemByNickname(@Param("deviceNickname")String deviceNickname);

}