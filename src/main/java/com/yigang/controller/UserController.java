package com.yigang.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yigang.entity.User;
import com.yigang.entity.UserSearch;
import com.yigang.service.UserService;
import com.yigang.utils.ResultUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("getAllUserList")
	@ResponseBody
	public ResultUtil getAllUserList(Integer page, Integer limit,UserSearch search) {
		return userService.getAllUserList(page, limit,search);
	}
	
	@RequestMapping("userList")
	public String userList() {
		return "jsp/user/userList";
	}
	
	@RequestMapping("editUser/{id}")
	public String editUser(@PathVariable("id") int id, HttpSession session) {
		User user = userService.getUserById(id);
		session.setAttribute("user", user);
		return "jsp/user/editUser";
	}
	
	@RequestMapping("deleteUserById")
	@ResponseBody
	public ResultUtil deleteUserById(int id) {
		return userService.deleteUserById(id);
	}
	
	@RequestMapping("addUser")
	public String userAdd(){
		return "jsp/user/addUser";
	}
	
	@RequestMapping("insertUser")
	@ResponseBody
	public ResultUtil insUser(User user){
		//防止浏览器提交
		User user1 = userService.selUserByUsername(user.getUsername());
		if(null != user1){
			return new ResultUtil(500,"用户名已存在，请重新填写！");
		}
		try {
			userService.insertUser(user);
			return ResultUtil.ok();
		} catch (Exception e) {
			return new ResultUtil(502,"网络错误，请检查网络！");
		}
	}
	
	@RequestMapping("checkUserByUsername/{username}")
	@ResponseBody
	public ResultUtil checkUserByUsername(@PathVariable("username")String username){
		User user = userService.selUserByUsername(username);
		if(user!=null){
			return new ResultUtil(500,"用户名已存在，请重新填写！");
		}
		return new ResultUtil(0);
	}
	
	@RequestMapping("updateUserStatusById")
	@ResponseBody
	public ResultUtil updateUserStatusById(int id, int status) {
		return userService.updateUserStatusById(id, status);
	}

	@RequestMapping("updateUser")
	@ResponseBody
	public ResultUtil updateUser(User user) throws ParseException {
		return userService.updateUser(user);
	}

}
