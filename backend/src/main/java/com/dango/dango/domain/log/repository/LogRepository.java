package com.dango.dango.domain.log.repository;

import com.dango.dango.domain.log.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    @Query(value = "select l from Log l where l.refrigerator.id = :refrigeratorId and l.exist = :isExist")
    List<Log> findAllByRefrigeratorId(@Param("refrigeratorId") Long refrigeratorId, @Param("isExist") Boolean isExist);

    @Query(value = "select l from Log l where l.refrigerator.id = :refrigeratorId and l.exist = true and l.inputTime <= :date")
    List<Log> findAllOldItems(@Param("refrigeratorId") Long refrigeratorId, @Param("date") LocalDateTime date);
}
