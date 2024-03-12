package com.dango.dango.domain.user.service;

import com.dango.dango.domain.user.entity.User;

public interface UserService {
	User findUser(Long id);
	User findUserByUsername(String username);
	User findUserByToken()
	User addUser(User user);
	User modifyUserNickName(Long id,String nickname);
	User modifyUserPassword(Long id,String password);
	void deleteUser(Long id);

	void duplicateUsername(String username);
	void duplicateNickname(String nickname);

}
