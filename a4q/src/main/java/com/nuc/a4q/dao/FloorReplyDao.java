package com.nuc.a4q.dao;

import com.nuc.a4q.entity.FloorReply;

public interface FloorReplyDao {
	/**
	 * 增加楼回复
	 * 
	 * @param floorReply
	 * @return
	 */
	public Integer insertFloorReply(FloorReply floorReply);

	/**
	 * 删除楼回复信息
	 * 
	 * @param floorReply
	 * @return
	 */
	public Integer deleteFloorReply(FloorReply floorReply);
}
