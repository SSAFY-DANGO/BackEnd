package com.dango.dango.domain.detectLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dango.dango.domain.detectLog.entity.DetectLog;

public interface DetectLogRepository extends JpaRepository<DetectLog,Long>{}
