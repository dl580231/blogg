package com.nuc.a4q.dao;

import java.util.List;

import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.FloorNotice;

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
	
	/**
	 * 評論通知的邏輯刪除
	 * @param floorId
	 * @return
	 */
	public Integer lookOverDelete(Integer floorId);
	
	/**
	 * 删除所有通知
	 * @param userId
	 * @return
	 */
	public Integer lookOverDeleteAll(Integer userId);
	
	/**
	 * 评论通知的查看操作
	 * @param floorId
	 * @return
	 */
	public Integer lookOver(Integer floorId);
	
	/**
	 * 獲取消息通知的樓信息
	 * @return
	 */
	public List<FloorNotice> getFloorList(Integer userId);
	
	/**
	 * 获取楼回复的帖子
	 * 的发帖人
	 * @param floorId
	 * @return
	 */
	public Integer getDeployUser(Integer floorId);
}
