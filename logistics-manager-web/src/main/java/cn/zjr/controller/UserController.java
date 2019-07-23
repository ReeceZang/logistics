package cn.zjr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

import cn.zjr.dto.UserDto;
import cn.zjr.pojo.User;
import cn.zjr.service.IUserService;
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	
	/**
	 * 查询所有用户
	 * @param user
	 * @param m
	 * @return
	 */
	@RequestMapping("/query.do")
	public String queryUser(User user,Model m) {
		List<User> list = userService.query(user);
		m.addAttribute("users", list);
		return "user/user";
		
	}
	
	/**
	 * 处理添加和修改操作之前执行的方法
	 * 该方法执行完成后会跳转到对应的 添加或者修改页面
	 * @return
	 */
	@RequestMapping("/getAddOrUpdateInfo.action")
	public String getAddOrUpdateInfo(Model model,Integer userId) {
		Map<String,Object> map = userService.getAddOrUpdateInfo(userId);
		if (map.containsKey("roles")) {
			model.addAttribute("roles", map.get("roles"));
		}
		//判断是更新还是添加
		if (userId !=null) {
			//id存在是更新操作
			model.addAttribute("userDto", map.get("userDto"));
		}
		return "user/user-update";
	}
	/**
	 * 具体的添加或者修改数据操作
	 * @return
	 */
	@RequestMapping("/saveOrUpdate.action")
	public String saveOrUpdate(UserDto userDto) {
		userService.saveOrUpdate(userDto);
		return "redirect:/user/userPage.action";
		
	}
	/**
	 * 删除用户以及角色信息
	 * @param userId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/deleteUser.action")
	public void deleteUser(Integer userId ,HttpServletResponse response) throws IOException {
		boolean flag = userService.deleteUserAndRole(userId);
		response.setContentType("text/html;charset=utf-8");
		if (flag) {
			//删除成功
			response.sendRedirect("/user/userPage.action");
		}else {
			//删除失败
			PrintWriter pw = response.getWriter();
			pw.write("<script type='text/javascript'");
			pw.write("alert('该数据不能被删除！');");
			pw.write("location.href='/user/userPage.action'");
			pw.write("</script>");
			pw.flush();
			pw.close();
			
		}
		
	}
	/**
	 * 用户分页显示仅限管理员可见
	 * @param dto
	 * @param model
	 * @return
	 */
	@RequiresRoles("管理员")
	@RequestMapping("/userPage.action")
	public String showUserByPage(UserDto dto,Model model) {
		model.addAttribute("dto", dto);
		PageInfo<User> pageModel = userService.queryPage(dto);
		model.addAttribute("pageModel", pageModel);
		return "user/userPage";
	}
}
