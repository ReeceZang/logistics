package cn.zjr.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zjr.dto.OrderDto;
import cn.zjr.service.IOderService;

/**
 * 订单管理
 * @author DELL
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	private IOderService orderService;
	
	/**
	 * 获取更新的信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderUpdate.action")
	public String OrderUpdateInfo(Integer id,Model model) {
		orderService.getUpdateInfo(id,model);
		return "order/order-update";
		
	}
	/**
	 * 添加订单
	 * @param dto
	 * @return
	 */
	@RequestMapping("/saveOrder.action")
	@ResponseBody
	public String saveOrder(@RequestBody OrderDto dto) {
		orderService.addOrder(dto);
		return "success-order";
	}
}
