package com.yigang.dao;

import java.util.List;

import com.yigang.entity.Role;

public interface RoleDao {

	List<Role> selRoles();

	Role getRoleById(Long id);

	void updateByKey(Role role);

	Role selectRoleByRoleName(String roleName);

	void delRole(Long roleId);

	void insertRole(Role role);

}
