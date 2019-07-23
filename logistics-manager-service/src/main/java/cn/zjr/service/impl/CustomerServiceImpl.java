package cn.zjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zjr.dto.CustomerDto;
import cn.zjr.mapper.CustomerMapper;
import cn.zjr.pojo.BasicData;
import cn.zjr.pojo.Customer;
import cn.zjr.pojo.CustomerExample;
import cn.zjr.pojo.Role;
import cn.zjr.pojo.User;
import cn.zjr.service.IBasicService;
import cn.zjr.service.ICustomerService;
import cn.zjr.service.IUserService;
import cn.zjr.utils.Constant;
@Service
public class CustomerServiceImpl implements ICustomerService{
	@Resource
	private CustomerMapper customerdao;
	@Resource
	private IUserService userService;
	@Resource
	private IBasicService basicService;
	 /**
     * 新增客户
     *    查询 所有的角色是业务员的用户
     *    查询 常用区间 基础数据
     * 修改客户
     *    查询 所有的角色是业务员的用户
     *    查询 常用区间 基础数据
     *    根据客户ID 查询详细信息
     */

	@Override
	public void getUpdateInfo(Integer id, Model model) {
		//1.查询所有具有业务员角色的用户信息
		List<User> userList = userService.queryUserByRoleName(Constant.ROLE_SAL);
		//2.查询常用区间的基础数据
		List<BasicData> data = basicService.getBasicDataByParentName(Constant.BASIC_COMMON_INTERVAL);
		if (id != null && id > 0) {
			//是更新操作   
			Customer customer = new Customer();
			customer.setCustomerId(id);
			//查询更新需要的数据
			List<CustomerDto> list = customerdao.queryView(customer);
			if (list !=null && list.size() ==1) {	
				model.addAttribute("dto", list.get(0));
			}
		}
		model.addAttribute("users", userList);
		model.addAttribute("data", data);
		
		
	}
	/**
	 * 新增客戶
	 */
	@Override
	public void addCustomer(Customer customer) {
		customerdao.insertSelective(customer);
		
	}
	/**
	 * 客户分页
	 */
	
	public PageInfo<CustomerDto> customerPage(CustomerDto dto,User user){
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		//获取角色信息
		List<Role> roles = userService.queryRoleByUserId(user.getUserId());
		boolean flag = false;
		if (roles !=null && roles.size() > 0) {
			for (Role role : roles) {
				//判断用户角色
				if (role.getRoleName().equals(Constant.ROLE_ADMIN) || role.getRoleName().equals(Constant.ROLE_OPERATOR)) {
					//如果角色为管理员或操作员，则可以执行查询操作
					flag=true;
					break;
				}
			}
		}
			//业务员限制查询
			Customer customer = new Customer();
			if (flag == false) {
				customer.setUserId(user.getUserId());
			}
			List<CustomerDto> customers = customerdao.queryView(customer);
		return new PageInfo<>(customers);
	}
	/**
	 * 更新客户
	 */
	@Override
	public void updateCustomer(Customer customer) {
		customerdao.updateByPrimaryKeySelective(customer);
		
	}
	@Override
	public void deleteCustomerById(Integer id) {
		customerdao.deleteByPrimaryKey(id);
		
	}
	@Override
	public List<Customer> query() {
		CustomerExample example = new CustomerExample();
		List<Customer> list = customerdao.selectByExample(example);
		return list;
	}
	@Override
	public Integer queryBaseIdByCustomerId(Integer customerId) {
		Customer customer = customerdao.selectByPrimaryKey(customerId);
		if (customer.getBaseId() != 0) {
			Integer baseId = customer.getBaseId();
			return baseId;			
		}
		
		return null;
	} 

}
