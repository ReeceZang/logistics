package cn.zjr.controller;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

import cn.zjr.dto.CustomerDto;
import cn.zjr.pojo.Customer;
import cn.zjr.pojo.User;
import cn.zjr.service.ICustomerService;
import cn.zjr.utils.Constant;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Resource
	private ICustomerService customerService;
	/**
	 * 新增客戶前的操作
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/addOrUpdate.action")
	public String getUpdateInfo(Integer id,Model model) {
		customerService.getUpdateInfo(id, model);
		return "customer/customer-update";
	}
	/**
	 * 更新或添加操作
	 * @param customer
	 * @return
	 */
	@RequestMapping("/saveOrUpdate.action")
	public String saveOrUpdate(Customer customer) {
		if (customer.getCustomerId() != null && customer.getCustomerId() > 0) {
			//表示是更新操作
			customerService.updateCustomer(customer);
			return "/success-update";
		}else {
			//添加操作
			customerService.addCustomer(customer);
		}
		return "/success";
	}
	/**
	 * 分页查询客户
	 * @param customerDto
	 * @param model
	 * @return
	 */
	@RequestMapping("/querybasic.action")
	public String querybasic(CustomerDto dto,Model model) {
		//获取用户登录信息
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		PageInfo<CustomerDto> pageList = customerService.customerPage(dto, user);
		model.addAttribute(Constant.PAGE_MODEL, pageList);
		return "customer/customer";
	} 
	/**
	 * 获取baseId通过customerId
	 * @param customerId
	 * @return
	 */
	@RequestMapping("/queryBaseIdByCustomerId.action")
	public String queryBaseIdByCustomerId(Integer customerId) {
		Integer baseId = customerService.queryBaseIdByCustomerId(customerId);
		return "order/order-update";
	}
	/**
	 * 删除客户信息
	 * 只有业务员和操作员有此权限
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCustomer.action")
	public String deleteCustomerById(Integer id) {
		customerService.deleteCustomerById(id);
		
		return "redirect:/customer/querybasic.action";
		
	}


}
