package cn.zjr.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 登录退出
 * @author DELL
 *
 */
@Controller
public class LoginController {
	/**
	 * 登录验证错误信息提示
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.action")
	public String login(Model model,HttpServletRequest request) {
		Object exception = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (exception != null) {
			System.out.println(exception.toString());
		}
		if (UnknownAccountException.class.getName().equals(exception)) {
			System.out.println("账号异常："+exception);
			model.addAttribute("msg", "账号不正确");
		}else if (IncorrectCredentialsException.class.getName().equals(exception)) {
			System.out.println("密码错误："+exception);
			model.addAttribute("msg", "密码错误！");
		}else {
			System.out.println("其他错误...");
			model.addAttribute("msg", "其他错误");
		}
		return "login";
	}
	/**
	 * 安全退出
	 * @return
	 */
	@RequestMapping("/logout.action")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "login";
	}
}
