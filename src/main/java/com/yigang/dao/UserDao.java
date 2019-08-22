package com.yigang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yigang.entity.User;
import com.yigang.entity.UserSearch;
import com.yigang.utils.ResultUtil;

public interface UserDao {

	List<User> getAllUserList(UserSearch search);

	User getUserById(int id);

	void updateUserStatusById(@Param("id")int id, @Param("status")int status);

	void updateUser(User user);

	User selUserByUsername(String username);

	void insertUser(User user);

	void deleteUserById(int id);

}
