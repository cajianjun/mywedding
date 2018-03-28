package com.cjj.response;

public class PageResponse {
	/**
	 * 总页码
	 */
	protected Long totalPages;
	/**
	 * 页码包含个数
	 */
	protected Integer pageSize;
	/**
	 * 当前页码
	 */
	protected Integer curPage;
	
	protected Object data;
	
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public PageResponse() {
		super();
	}
	
}
