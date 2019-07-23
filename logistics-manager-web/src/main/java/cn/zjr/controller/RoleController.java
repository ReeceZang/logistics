package cn.zjr.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zjr.pojo.Role;
import cn.zjr.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private IRoleService roleservice;
	@RequiresRoles("管理员")
	@RequestMapping("/query.do")
	public String queryRoles(Model m,Role role) {
		List<Role> list = roleservice.queryRole(role);
		m.addAttribute("list", list);
		return "role/role";
		
	}
}
