package com.dango.dango.domain.user.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dango.dango.domain.user.dto.UserProfileResponse;
import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.mapper.UserMapper;
import com.dango.dango.domain.user.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
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
	public User findUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(
			()->new UsernameNotFoundException("해당하는 이메일을 조회하지 못하였습니다")
		);
		return user;
	}

	@Override
	public User findUserByToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getPrincipal().toString();
			User user = findUserByUsername(username);
			return user;
		}
		else{
			throw new UsernameNotFoundException("잘못된 요청입니다");
		}
	}

	@Override
	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User modifyUserNickName(Long id, String nickname) {
		User user = findUser(id);
		user.setNickname(nickname);
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

	@Override
	public void duplicateUsername(String username) {
		boolean exist = userRepository.existsByUsername(username);
		if(exist) throw new RuntimeException("중복되는 이메일입니다");
	}

	@Override
	public void duplicateNickname(String nickname) {
		boolean exist = userRepository.existsByNickname(nickname);
		if(exist) throw new RuntimeException("중복되는 닉네임입니다");
	}

	@Override
	public UserProfileResponse getProfile() {
		UserProfileResponse userProfileResponse = UserMapper.INSTANCE.UserToUserProfile(findUserByToken());
		return userProfileResponse;
	}
}
