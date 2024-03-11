package com.dango.dango.domain.user.service;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(
			()-> new EntityNotFoundException(
				"해당하는 아이디의 유저를 조회하지 못하였습니다."
			)
		);
		return user;
	}

	@Override
	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public User modifyUserNickName(Long id, String nickname) {
		User user = findUser(id);
		user.setNickName(nickname);
		return user;
	}

	@Override
	@Transactional
	public User modifyUserPassword(Long id, String password) {
		User user = findUser(id);
		user.setPassword(password);
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		User user = findUser(id);
		user.setDeleted(true);
		user.setDeleteTime(LocalDateTime.now());
	}
}
