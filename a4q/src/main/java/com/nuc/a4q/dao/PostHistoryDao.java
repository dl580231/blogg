package com.nuc.a4q.dao;

import java.util.List;

import com.nuc.a4q.entity.PostHistory;

public interface PostHistoryDao {
	/**
	 * 增加查询帖子记录
	 * @param history
	 * @return
	 */
	public Integer insertHistory(PostHistory history);
	
	/**
	 * 根据相应条件获得查询记录
	 * @param history
	 */
	public List<PostHistory> getPostHistory(PostHistory history);
	
	/**
	 * 更新浏览记录时间
	 * @param history
	 * @return
	 */
	public Integer updatePostHistory(PostHistory history);
}
