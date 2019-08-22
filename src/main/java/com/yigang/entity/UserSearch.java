package com.yigang.entity;

public class UserSearch {

	private String username;
	private String sex;
	private String status;
	private String createTimeStart;
	private String createTimeEnd;
	private String birthdayStart;
	private String birthdayEnd;
	private int roleId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getBirthdayStart() {
		return birthdayStart;
	}
	public void setBirthdayStart(String birthdayStart) {
		this.birthdayStart = birthdayStart;
	}
	public String getBirthdayEnd() {
		return birthdayEnd;
	}
	public void setBirthdayEnd(String birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "UserSearch [username=" + username + ", sex=" + sex + ", status=" + status + ", createTimeStart="
				+ createTimeStart + ", createTimeEnd=" + createTimeEnd + ", birthdayStart=" + birthdayStart
				+ ", birthdayEnd=" + birthdayEnd + ", roleId=" + roleId + "]";
	}

	
}
