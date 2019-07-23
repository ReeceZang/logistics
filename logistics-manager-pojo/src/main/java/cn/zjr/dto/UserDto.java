package cn.zjr.dto;

import java.util.List;

import cn.zjr.pojo.Role;
import cn.zjr.pojo.User;
/**
 * 收集提交的数据
 * @author DELL
 *
 */
public class UserDto extends BasePage{
	private User user;
	private List<Integer> roIds;
	private List<Role> roles;
	
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Integer> getRoIds() {
		return roIds;
	}
	public void setRoIds(List<Integer> roIds) {
		this.roIds = roIds;
	}

	
}
