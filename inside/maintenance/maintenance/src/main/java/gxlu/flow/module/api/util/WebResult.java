package gxlu.flow.module.api.util;

import java.util.ArrayList;
import java.util.List;

import gxlu.flow.framework.page.PageInfo;

public class WebResult {
	private Integer code;
	private String message;
	private List<Object> data;
	private Long total;
	private Integer pageNum;
	public WebResult() {
		
	}
	public WebResult(PageInfo pageInfo) {
		this.code=0;
		this.message="";
		this.data=new ArrayList<Object>();
		this.data.addAll(pageInfo.getData());
		this.total=pageInfo.getRecordsTotal();
	}
	public WebResult(Object obj) {
		this.code=0;
		this.data=new ArrayList<Object>();
		if(obj instanceof ArrayList) {
			this.data.addAll((ArrayList)obj);
		}else {
			this.data.add(obj);
		}
		this.message="";
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	
}
