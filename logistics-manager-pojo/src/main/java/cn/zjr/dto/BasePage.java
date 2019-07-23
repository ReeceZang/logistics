package cn.zjr.dto;
/**
 * 分页
 * 默认起始于第一页
 * 每页显示五条数据
 * @author DELL
 *
 */
public class BasePage {
	//当前页
	private int pageNum=1;
	//每页显示的数据条数
	private int pageSize=5;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
