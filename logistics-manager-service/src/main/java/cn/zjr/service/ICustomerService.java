package cn.zjr.service;

import java.util.List;

import org.springframework.ui.Model;

import com.github.pagehelper.PageInfo;

import cn.zjr.dto.CustomerDto;
import cn.zjr.pojo.Customer;
import cn.zjr.pojo.User;

public interface ICustomerService {
	public List<Customer> query();
	public void getUpdateInfo(Integer id,Model model);
	public void addCustomer(Customer customer);
	public PageInfo<CustomerDto> customerPage(CustomerDto dto,User user);
	public void updateCustomer(Customer customer);
	public void deleteCustomerById(Integer id);
	public Integer queryBaseIdByCustomerId(Integer customerId);
}
