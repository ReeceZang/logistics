package cn.zjr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zjr.dto.UserDto;
import cn.zjr.mapper.CustomerMapper;
import cn.zjr.mapper.UserMapper;
import cn.zjr.pojo.Customer;
import cn.zjr.pojo.CustomerExample;
import cn.zjr.pojo.Role;
import cn.zjr.pojo.User;
import cn.zjr.pojo.UserExample;
import cn.zjr.service.IRoleService;
import cn.zjr.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userdao;
	@Resource
	private CustomerMapper customerdao;
	@Resource
	private IRoleService roledao;
	@Override
	public List<User> query(User user) {
		UserExample example = new UserExample();
		if (user != null ) {
			if (!"".equals(user.getUserName()) && user.getUserName() != null) {
				//根据账号查询
				example.createCriteria().andUserNameEqualTo(user.getUserName());
			}
		}
		
		return userdao.selectByExample(example);
	}

	@Override
	public int addUser(User user) {
	
		return userdao.insertSelective(user);
	}

	@Override
	public int updateUser(User user) {

		return userdao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int deleteUserById(Integer id) {
		return userdao.deleteByPrimaryKey(id);
	}
	
	@Override
	public Map<String, Object> getAddOrUpdateInfo(Integer userId){
		Map<String, Object> map = new HashMap<>();
		//查询所有的角色信息
		List<Role> list = roledao.queryRole(null);
		map.put("roles", list);
		if (userId != null) {
			//id存在：是更新操作
			//1.根据用户编号查询出对应的详细信息
			User user = userdao.selectByPrimaryKey(userId);
			//2.查询出该用户具有的角色信息
			List<Role> userRoles = roledao.queryRoleByUserId(userId);
			UserDto userDto = new UserDto();
			userDto.setUser(user);
			userDto.setRoles(userRoles);
			map.put("userDto", userDto);
		}	
		return map;
	}

	@Override
	public void saveOrUpdate(UserDto userDto) {
		// 1. 添加用户数据  对用户的密码 加密  获取盐值
		//获取User对象
		User user = userDto.getUser();
		if (user != null &&user.getUserId() != null) {
			//表示更新
			this.updateUser(user);
			//根据编号删除具有的角色关系
			userdao.deleteByRoleRelationById(user.getUserId());
		}else {
			//生成盐值
			String salt = UUID.randomUUID().toString();
			//设置盐值
			user.setU1(salt);
			//对用户密码进行加密
			user.setPassword(new Md5Hash(user.getPassword(), salt, 1024).toString());
			this.addUser(user);
			
		}
		//获取关联的角色信息
		List<Integer> roleIds= userDto.getRoIds();
		//判断添加还是修改数据
		if (roleIds !=null) {
			for (Integer roleId : roleIds) {
				userdao.insertUserAndRoleId(user.getUserId(), roleId);
			}
		}
	}
	
	@Override
	public boolean deleteUserAndRole(Integer userId) {
		//判断账号是否可以被删除
		CustomerExample example = new CustomerExample();
		example.createCriteria().andUserIdEqualTo(userId);
		List<Customer> customers = customerdao.selectByExample(example);
		if (customers != null && customers.size()>0) {
			//表示不能删除该记录
			return false;
		}
		
		//删除关联
		userdao.deleteByRoleRelationById(userId);
		//删除角色
		this.deleteUserById(userId);
		return true;
		
	}

	@Override
	public PageInfo<User> queryPage(UserDto dto) {
		PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
		List<User> list = this.query(dto.getUser());
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		
		return pageInfo;
	}

	@Override
	public List<Role> queryRoleByUserId(Integer userId) {

		return roledao.queryRoleByUserId(userId);
	}

	@Override
	public List<User> queryUserByRoleName(String roleName) {
		
		return userdao.queryUserByRoleName(roleName);
	}
}
