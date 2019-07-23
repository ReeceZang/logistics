package cn.zjr.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

import cn.zjr.pojo.Role;
import cn.zjr.pojo.User;
import cn.zjr.service.IUserService;
/**
 * 自定义的Realm
 * @author DELL
 *
 */
public class MyRealm extends AuthorizingRealm {
	@Resource
	private IUserService userService;
	/**
	 * 认证的方法
	 */
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取提交的账号
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		//获取登录的帐号
		String userName = t.getUsername();
		User user = new User();
		user.setUserName(userName);
		List<User> list = userService.query(user);
		System.out.println(list.get(0).getUserName()+":"+list.get(0).getPassword());
		if (list == null || list.size()>1) {
			//账号不存在或多于一个都返回null
			return null;
		}
		user=list.get(0);
		 
		return new SimpleAuthenticationInfo(user, user.getPassword(),new SimpleByteSource(user.getU1().getBytes()), "myRealm");
	}
	/**
	 * 授权的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取认证的信息
		User user = (User) principals.getPrimaryPrincipal();
		if (user==null) {
			return null;
		}
		//获取此次登录的用户的权限
		List<Role> roles = userService.queryRoleByUserId(user.getUserId());
		if (roles != null && roles.size() > 0) {
			
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : roles) {
				//将用户具有的角色信息保存到info对象中
				info.addRole(role.getRoleName());	
			}
			return info;
		}
		return null;
	}


}
