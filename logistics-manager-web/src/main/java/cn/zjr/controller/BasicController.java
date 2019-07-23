package cn.zjr.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zjr.pojo.BasicData;
import cn.zjr.service.IBasicService;

@Controller
@RequestMapping("/basic")
public class BasicController {
	@Resource
	private IBasicService basicService;
	/**
	 * 查询所有基础数据
	 * @param bData
	 * @param model
	 * @return
	 */
	@RequestMapping("/querybasic.action")
	public String queryBasic(BasicData bData,Model model) {
		List<BasicData> children = basicService.queryChild(bData);
		if (bData !=null && bData.getParentId() !=null &&bData.getParentId() > 0) {
			bData.setBaseId(bData.getParentId());
			bData.setParentId(null);
			children.addAll(basicService.queryChild(bData));
		}
		model.addAttribute("child", children);
		//查询所有大类的数据
		
		List<BasicData> parents = basicService.queryParent();
		model.addAttribute("parent", parents);
		return "basic/basic";
	}
	/**
	 * 更新基础数据之前的操作
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAddOrUpdateData.action")
	public String getAddOrUpdateData(Integer id,Model model) {
		basicService.getAddOrUpdateInfo(id,model);
		return "basic/basic-update";
	}
	/**
	 * 更新数据
	 * @param bd
	 * @return
	 */
	@RequestMapping("/saveOrUpdate.action")
	public String saveOrUpdate(BasicData bd) {
		if (bd.getParentId() == 0) {
			bd.setParentId(null);
		}
		if (bd.getBaseId() != null && bd.getBaseId() > 0 ) {
			//表示更新数据
			basicService.updateBasicData(bd);
		}else {
			//表示添加数据
			basicService.addBasicData(bd);			
		}
		return "redirect:/basic/querybasic.action";
	}
	/**
	 * 删除数据操作
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDataById.action")
	public String deleteDataById(Integer id) {
		basicService.deleteBasicData(id);
		return "redirect:/basic/querybasic.action";
		
	}
	
	
	
}
