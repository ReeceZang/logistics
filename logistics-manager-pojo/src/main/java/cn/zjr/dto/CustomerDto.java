package cn.zjr.dto;

import cn.zjr.pojo.Customer;
/**
 * 客户传输的数据
 * @author DELL
 *
 */
public class CustomerDto extends BasePage{
	private Customer customer;
	//业务员
	private String saleMan;
	//常用区间
	private String interval;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getSaleMan() {
		return saleMan;
	}
	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	
}
