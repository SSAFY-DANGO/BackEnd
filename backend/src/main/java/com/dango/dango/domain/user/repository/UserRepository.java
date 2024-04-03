package com.dango.dango.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dango.dango.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByNickname(String nickname);
}
