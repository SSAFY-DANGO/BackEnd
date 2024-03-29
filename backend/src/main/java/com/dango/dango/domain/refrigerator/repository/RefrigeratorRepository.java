package com.dango.dango.domain.refrigerator.repository;

import com.dango.dango.domain.refrigerator.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
    Optional<Refrigerator> findByNickname(String nickname);

}
