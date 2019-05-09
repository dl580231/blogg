package com.nuc.a4q.entity;

public class PageDivide {
	private Integer rowCount;// 数据总数量
	private Integer currentPage = 1;// 当前页数
	private Integer pageCount;// 页面数量
	private Integer pageRowCount = 5;// 一页的数据量
	private Object entityList;// 用户信息列表

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageRowCount() {
		return pageRowCount;
	}

	public void setPageRowCount(Integer pageRowCount) {
		this.pageRowCount = pageRowCount;
	}

	public Object getEntityList() {
		return entityList;
	}

	public void setEntityList(Object entityList) {
		this.entityList = entityList;
	}

	@Override
	public String toString() {
		return "PageDivide [rowCount=" + rowCount + ", currentPage=" + currentPage + ", pageCount=" + pageCount
				+ ", pageRowCount=" + pageRowCount + ", entityList=" + entityList + "]";
	}

}
