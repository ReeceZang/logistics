package cn.zjr.service;

import java.util.List;

import cn.zjr.pojo.Role;

public interface IRoleService {
	
	/**
	 * 查询
	 * @param role
	 */
	public List<Role> queryRole(Role role);
	/**
	 * 添加角色
	 * @param role

	 */
	public void addRole(Role role);
	/**
	 *	更新 
	 * @param role
	 */
	public void updateRole(Role role);
	/**
	 * 删除
	 * @param role
	 */
	public void deleteRole(Role role);
	/**
	 * 根据userId查询角色
	 * @param userId
	 * @return
	 */
	public List<Role> queryRoleByUserId(Integer userId);
	
}
