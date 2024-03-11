package com.dango.dango.domain.user.service;

import com.dango.dango.domain.user.entity.User;

public interface UserService {
	User findUser(Long id);
	void addUser(User user);
	User modifyUserNickName(Long id,String nickname);
	User modifyUserPassword(Long id,String password);
	void deleteUser(Long id);
}
