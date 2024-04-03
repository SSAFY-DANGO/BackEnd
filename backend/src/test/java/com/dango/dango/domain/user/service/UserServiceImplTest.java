package com.dango.dango.domain.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.antlr.v4.runtime.misc.Interval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dango.dango.domain.user.entity.User;
import com.dango.dango.domain.user.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	private UserService userService;

	private Long userId;
	private User mockUser;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		userService = new UserServiceImpl(userRepository);

		userId = 1L;

		mockUser = User.builder()
			.id(userId)
			.username("test@naver.com")
			.password("password")
			.nickname("nickname")
			.build();
	}
	@Test
	void 사용자가_있을경우_유저조회() {


		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

		User foundUser = userService.findUser(userId);

		assertEquals(mockUser, foundUser);

	}
	@Test
	void 사용자가_없을경우_유저조회(){
		Long userId = 2L;

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
			userService.findUser(userId);
		});

		assertEquals("해당하는 아이디의 유저를 조회하지 못하였습니다.", exception.getMessage());
	}

	@Test
	void 사용자가_제대로_저장되었을경우() {

	}

	@Test
	void 사용자_닉네임_수정() {
		String nickname = "test";
		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
		User user = userService.modifyUserNickName(userId,nickname);
		assertEquals(user.getNickname(),nickname);
	}

	@Test
	void 사용자_비밀번호_수정() {
		String password = "pass";
		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
		User user = userService.modifyUserPassword(userId,password);
		assertEquals(user.getPassword(),password);
	}

	@Test
	void 사용자_삭제() {
		when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

		userService.deleteUser(userId);
		User user = userService.findUser(userId);

		assertEquals(user.isDeleted(),true);
	}
}