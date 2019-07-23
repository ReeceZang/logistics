package cn.zjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zjr.mapper.RoleMapper;
import cn.zjr.pojo.Role;
import cn.zjr.pojo.RoleExample;
import cn.zjr.service.IRoleService;
@Service
public class RoleServiceImpl implements IRoleService {
	@Resource
	private RoleMapper roledao;
	@Override
	public List<Role> queryRole(Role role) {
		RoleExample example = new RoleExample();
		
		return roledao.selectByExample(example);
		
	}

	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> queryRoleByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return roledao.queryRoleByUserId(userId);
	}
	
}
