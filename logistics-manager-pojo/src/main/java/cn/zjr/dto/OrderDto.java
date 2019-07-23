package cn.zjr.dto;

import java.util.List;

import cn.zjr.pojo.Order;
import cn.zjr.pojo.OrderDetail;

public class OrderDto extends Order{
	 private List<OrderDetail> orderDetails;

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	 
}
