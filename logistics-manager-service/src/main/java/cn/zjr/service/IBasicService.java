package cn.zjr.service;

import java.util.List;

import org.springframework.ui.Model;

import com.github.pagehelper.PageInfo;

import cn.zjr.dto.BasicDto;
import cn.zjr.pojo.BasicData;

public interface IBasicService {
	public List<BasicData> query(BasicData bData);
	public List<BasicData> queryChild(BasicData bData);
	public List<BasicData> queryParent();
	public PageInfo<BasicData> queryPage(BasicDto dto);
	public Integer addBasicData(BasicData bData);
	public Integer deleteBasicData(Integer id);
	public Integer updateBasicData(BasicData bData);
	public void getAddOrUpdateInfo(Integer id, Model model);
	public List<BasicData> getBasicDataByParentName(String parentName);
	
	
}
