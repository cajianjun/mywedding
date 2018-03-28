package com.cjj.request;

import io.swagger.annotations.ApiModelProperty;

public class PageRequest {
	@ApiModelProperty(value = "每页数量默认10", required = true)
	protected Integer pageSize;
	@ApiModelProperty(value = "当前页码默认1", required = true)
	protected Integer page;
	public Integer getPageSize() {
		if(pageSize == null || pageSize <= 0)return 10;
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		if(page == null || page <= 0)return 1;
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public PageRequest() {
		super();
	}
	
}
