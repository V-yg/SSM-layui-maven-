package com.yigang.entity;

import java.util.Date;

public class AdminLog {

	private Integer id;
	
	private String adminUsername;
	
	private String loginIp;
	
	private Date loginTime;
	
	private Date logoutTime;
	
	private int isSafeExit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
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

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public int getIsSafeExit() {
		return isSafeExit;
	}

	public void setIsSafeExit(int isSafeExit) {
		this.isSafeExit = isSafeExit;
	}

	@Override
	public String toString() {
		return "AdminLog [id=" + id + ", adminUsername=" + adminUsername + ", loginIp=" + loginIp + ", loginTime="
				+ loginTime + ", logoutTime=" + logoutTime + ", isSafeExit=" + isSafeExit + "]";
	}
	
	
	
}
