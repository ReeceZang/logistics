package cn.zjr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	/**
	 * 跳转到login.jsp
	 * @return
	 */
	@RequestMapping("/")
	public String showLogin() {
		return "login";
	}
	
  
    /**
     * restful 风格
     *    传递的是什么数据就跳转到对应的页面
     * @param path
     * @return
     */
    @RequestMapping("/{path}")
    public String showTop(@PathVariable String path){
		// /WEB-INF/jsp/main.jsp    path=main
        return path;
    }
    
    
}


