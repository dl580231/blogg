package com.nuc.a4q.utils;

import com.nuc.a4q.entity.PageDivide;

public class PageUtil {
	/**
	 * 返回查询数据开始的位置
	 * 
	 * @param currentPage
	 * @param pageRowCount
	 * @return
	 */
	public static Integer getRowStart(Integer currentPage, Integer pageRowCount) {
		return (currentPage - 1) * pageRowCount;
	}

	/**
	 * 返回页面总数
	 * 
	 * @param rowCount
	 * @param pageRowCount
	 * @return
	 */
	public static Integer getPageCount(Integer rowCount, Integer pageRowCount) {
		return (rowCount - 1) / pageRowCount + 1;
	}

	/**
	 * 对pageDivide 实体类对象进行封装
	 * 
	 * @param pageDivide
	 * @param rowCount
	 * @param pageRowCount
	 * @param entityList
	 */
	public static void buildPageDivide(PageDivide pageDivide, Integer rowCount, Integer pageRowCount,
			Object entityList) {
		Integer pageCount = getPageCount(rowCount, pageRowCount);
		pageDivide.setEntityList(entityList);
		pageDivide.setPageCount(pageCount);
		pageDivide.setRowCount(rowCount);
	}
}