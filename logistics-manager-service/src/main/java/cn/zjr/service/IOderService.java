package cn.zjr.service;

import org.springframework.ui.Model;

import cn.zjr.dto.OrderDto;

public interface IOderService {
	
	void getUpdateInfo(Integer id, Model model);
	void addOrder(OrderDto dto);
}
