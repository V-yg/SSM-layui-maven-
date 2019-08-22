package com.yigang.entity;

import java.util.Date;

public class UserLog {

	private Integer id;
	
	private String userUsername;
	
	private String loginIp;
	
	private Date loginTime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	@Override
	public String toString() {
		return "UserLog [id=" + id + ", userUsername=" + userUsername + ", loginIp=" + loginIp + ", loginTime="
				+ loginTime + "]";
	}
	

}
