package com.yigang.service;

import com.yigang.entity.User;
import com.yigang.entity.UserSearch;
import com.yigang.utils.ResultUtil;

public interface UserService {

	ResultUtil getAllUserList(Integer page, Integer limit,UserSearch search);

	User getUserById(int id);

	ResultUtil updateUserStatusById(int id, int status);

	ResultUtil updateUser(User user);

	User selUserByUsername(String username);

	void insertUser(User user);

	ResultUtil deleteUserById(int id);

}
