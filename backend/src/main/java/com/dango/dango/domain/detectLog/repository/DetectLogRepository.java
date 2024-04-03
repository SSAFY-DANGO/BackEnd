package com.dango.dango.domain.detectLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dango.dango.domain.detectLog.entity.DetectLog;

import io.lettuce.core.dynamic.annotation.Param;

public interface DetectLogRepository extends JpaRepository<DetectLog, Long> {
	@Query(value = "select d from DetectLog d where d.device = :device and d.inputType = :inputType order by d.inputTime desc limit 1")
	DetectLog findAllLatestItem(@Param("device") String device, @Param("inputType") String inputType);
}
