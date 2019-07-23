package cn.zjr.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import cn.zjr.dto.UserDto;
import cn.zjr.pojo.Role;
import cn.zjr.pojo.User;

public interface IUserService {
	
	/**
	 * 查询
	 * @param user
	 * @return
	 */
	public List<User> query(User user);
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	public int addUser(User user);
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
	/**
	 * 删除
	 * @param user
	 * @return
	 */
	public int deleteUserById(Integer id);
	/**
	 * 获取用户添加或修改的数据
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getAddOrUpdateInfo(Integer userId);

	/**
	 * 添加或修改用户数据
	 * @param userDto   存放了user 和 roleId
	 */
	public void saveOrUpdate(UserDto userDto);
	/**
	 * 删除用户以及用户的角色信息
	 * @param userId
	 */
	public boolean deleteUserAndRole(Integer userId);
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 */
	public PageInfo<User> queryPage(UserDto dto);
	/**
	 * 通过用户id查询角色信息
	 * @param userId
	 * @return
	 */
	public List<Role> queryRoleByUserId(Integer userId);
	public List<User> queryUserByRoleName(String roleName);
}
