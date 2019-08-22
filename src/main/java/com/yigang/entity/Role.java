package com.yigang.entity;

public class Role {

	private Long roleId;

	private String roleName;

	private String roleRemark;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark == null ? null : roleRemark.trim();
	}

	@Override
	public String toString() {
		return "TbRoles [roleId=" + roleId + ", roleName=" + roleName + ", roleRemark=" + roleRemark + "]";
	}
}
