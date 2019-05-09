package com.nuc.a4q.dao;

import java.util.List;

import com.nuc.a4q.entity.Floor;

public interface FloorDao {
	/**
	 * 查询floorList
	 * 
	 * @param floor
	 * @return
	 */
	public List<Floor> queryFloorList(Floor floor);

	/**
	 * 增加floor信息
	 * 
	 * @param floor
	 * @return
	 */
	public Integer insertFloor(Floor floor);

	/**
	 * 删除楼信息
	 * 
	 * @param floorId
	 * @return
	 */
	public Integer deleteFloor(Floor floor);

	/**
	 * 查询指定postId的楼信息数量
	 * 
	 * @param podtId
	 * @return
	 */
	public Integer getFloorCountByPostId(Integer postId);
}
