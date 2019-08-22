package com.yigang.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yigang.dao.AdminDao;
import com.yigang.dao.UserDao;
import com.yigang.entity.AdminLog;
import com.yigang.entity.User;
import com.yigang.entity.UserSearch;
import com.yigang.service.UserService;
import com.yigang.utils.MD5Utils;
import com.yigang.utils.ResultUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public ResultUtil getAllUserList(Integer page, Integer limit,UserSearch search) {
		PageHelper.startPage(page,limit);
		List<User> users = userDao.getAllUserList(search);
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		ResultUtil resultUtil = new ResultUtil();
		resultUtil.setCode(0);
		resultUtil.setCount(pageInfo.getTotal());
		resultUtil.setData(pageInfo.getList());
		return resultUtil;
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public ResultUtil updateUserStatusById(int id, int status) {
		userDao.updateUserStatusById(id,status);
		return ResultUtil.ok();
	}

	@Override
	public ResultUtil updateUser(User user) {
		if(null != user.getPassword()) {
			user.setPassword(MD5Utils.md5(user.getPassword()));
		}
		userDao.updateUser(user);
		return ResultUtil.ok();
	}

	@Override
	public User selUserByUsername(String username) {
		return userDao.selUserByUsername(username);
	}

	@Override
	public void insertUser(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		user.setCreateTime(new Date());
		userDao.insertUser(user);
	}

	@Override
	public ResultUtil deleteUserById(int id) {
		userDao.deleteUserById(id);
		return ResultUtil.ok();
	}

}
