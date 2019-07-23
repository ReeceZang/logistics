package cn.zjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cn.zjr.dto.OrderDto;
import cn.zjr.mapper.OrderDetailMapper;
import cn.zjr.mapper.OrderMapper;
import cn.zjr.pojo.BasicData;
import cn.zjr.pojo.Customer;
import cn.zjr.pojo.OrderDetail;
import cn.zjr.pojo.User;
import cn.zjr.service.IBasicService;
import cn.zjr.service.ICustomerService;
import cn.zjr.service.IOderService;
import cn.zjr.service.IUserService;
import cn.zjr.utils.Constant;
@Service
public class OrderServiceImpl implements IOderService {
	@Resource
	private OrderMapper orderdao;
	@Resource
	private IUserService userservice;
	@Resource
	private IBasicService basicservice;
	@Resource
	private ICustomerService customerservice;
	@Resource
	private OrderDetailMapper detaildao;
	@Override
	public void getUpdateInfo(Integer id, Model model) {
		//1.查询所有具有业务员角色的用户信息
		List<User> users = userservice.queryUserByRoleName(Constant.ROLE_SAL);
		//2.查询常用区间的基础数据
		List<BasicData> intervals = basicservice.getBasicDataByParentName(Constant.BASIC_COMMON_INTERVAL);
		//3.付款方式
		List<BasicData> payments = basicservice.getBasicDataByParentName(Constant.BASIC_PAY_METHOD);
		//4.货运方式
		List<BasicData> transfer = basicservice.getBasicDataByParentName(Constant.BASIC_TRANSFER_TYPE);
		//5.取件方式
		List<BasicData> getMethods=basicservice.getBasicDataByParentName(Constant.BASIC_GET_TYPE);
		//6.查询客户信息
		List<Customer> customers = customerservice.query();
		//7.国家
		List<BasicData> countrys = basicservice.getBasicDataByParentName(Constant.BASIC_COMMON_INTERVAL);
		//8.单位
		List<BasicData> units = basicservice.getBasicDataByParentName(Constant.BASIC_UNIT);
		model.addAttribute("users", users);
		model.addAttribute("intervals", intervals);
		model.addAttribute("payments", payments);
		model.addAttribute("transfer", transfer);
		model.addAttribute("getMethods", getMethods);
		model.addAttribute("customers", customers);
		model.addAttribute("countrys", countrys);
		model.addAttribute("units", units);
		
	}
	/**
	 *新增订单
	 */
	@Override
	public void addOrder(OrderDto dto) {
		orderdao.insertSelective(dto);
		List<OrderDetail> orderDetails = dto.getOrderDetails();
		if (orderDetails != null && orderDetails.size() > 0) {
			for (OrderDetail orderDetail : orderDetails) {
				//添加订单详细信息
				orderDetail.setOrderId(dto.getOrderId());
				detaildao.insertSelective(orderDetail);
			}			
		}
	}
}
