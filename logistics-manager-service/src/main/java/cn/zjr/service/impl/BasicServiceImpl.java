package cn.zjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zjr.dto.BasicDto;
import cn.zjr.mapper.BasicDataMapper;
import cn.zjr.pojo.BasicData;
import cn.zjr.pojo.BasicDataExample;
import cn.zjr.pojo.BasicDataExample.Criteria;
import cn.zjr.service.IBasicService;
@Service
public class BasicServiceImpl implements IBasicService {
	@Resource
	private BasicDataMapper basicdao;
	@Override
	public List<BasicData> query(BasicData bData) {
		BasicDataExample example = new BasicDataExample();
		return basicdao.selectByExample(example);
	}
	/**
	 * 查询小类
	 */
	@Override
	public List<BasicData> queryChild(BasicData bData) {
		BasicDataExample example = new BasicDataExample(); 
		Criteria criteria = example.createCriteria();
		if (bData != null) {
			if (bData.getParentId() !=null && bData.getParentId() > 0) {
				//查询所有小类
				criteria.andParentIdEqualTo(bData.getParentId());
			}
			if (bData.getBaseId() !=null) {
				criteria.andBaseIdEqualTo(bData.getBaseId());
			}
		}
		return basicdao.selectByExample(example);
	}

	
	@Override
	public PageInfo<BasicData> queryPage(BasicDto dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		List<BasicData> list = this.query(dto.getBasic());
		return new PageInfo<>(list);
	}

	@Override
	public Integer addBasicData(BasicData bData) {
		return basicdao.insertSelective(bData);

	}

	@Override
	public Integer deleteBasicData(Integer id) {
		return basicdao.deleteByPrimaryKey(id);

	}

	@Override
	public Integer updateBasicData(BasicData bData) {
		return basicdao.updateByPrimaryKeySelective(bData);

	}
	/**
	 * 查询大类
	 */
	@Override
	public List<BasicData> queryParent() {
		BasicDataExample example = new BasicDataExample();
		example.createCriteria().andParentIdIsNull();
		return basicdao.selectByExample(example);
	}
	@Override
	public void getAddOrUpdateInfo(Integer id, Model model) {
		BasicDataExample example = new BasicDataExample();
		//没有父id说明是大类
		example.createCriteria().andParentIdIsNull();
		//查询所有的父类类型
		List<BasicData> parents=basicdao.selectByExample(example);
		model.addAttribute("parents", parents);
		if (id != null && id >0) {
			//说明是更新操作,根据id查询对应的数据信息
			BasicData basicData = basicdao.selectByPrimaryKey(id);
			model.addAttribute("basic", basicData);
			
		}
		
	}
	@Override
	public List<BasicData> getBasicDataByParentName(String parentName) {
		
		return basicdao.getBasicDataByParentName(parentName);
	}



}
