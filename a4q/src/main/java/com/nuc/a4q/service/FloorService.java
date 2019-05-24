package com.nuc.a4q.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.FloorDao;
import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.FloorNotice;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.utils.ResultUtil;

@Service
public class FloorService {
	@Autowired
	private FloorDao dao;

	/**
	 * 获取回帖的楼信息并且获得最佳恢复属于的楼层
	 * @param floor
	 * @param user
	 * @param isResolved
	 * @return
	 */
	public Result getFloorListWithNum(Floor floor, PersonInfo user, Integer isResolved) {
		Map<String, Object> model = new HashMap<>();
		if (user.getUserId() != null) {
			floor.setUser(user);
		}
		List<Floor> list = dao.queryFloorList(floor);
		model.put("list", list);
		if (isResolved == null) {
			return ResultUtil.success(model);
		} else {
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				Floor bestAnswer = list.get(i);
				Integer temp = bestAnswer.getFloorId();
				if (temp.equals(isResolved)) {
					num = i + 1;
					break;
				}
			}
			model.put("bestAnswer", num + "楼");
			return ResultUtil.success(model);
		}
	}

	/**
	 * 获得楼列表
	 * @param floor
	 * @param user
	 * @return
	 */
	public List<Floor> getFloorList(Floor floor, PersonInfo user) {
		if (user.getUserId() != null) {
			floor.setUser(user);
		}
		List<Floor> list = dao.queryFloorList(floor);
		return list;
	}


	public void removeFloor(Floor floor) {
		dao.deleteFloor(floor);
	}

	public Integer getFloorCountByPostId(Integer postId) {
		return dao.getFloorCountByPostId(postId);
	}

	public void addFloor(Floor floor, PersonInfo user, Post post) {
		if (user == null) {
			throw new LogicException("未登录状态");
		}
		if (post == null) {
			throw new LogicException("帖子信息为空");
		}
		floor.setPostId(post.getPostId());
		floor.setUser(user);
		floor.setCreateTime(new Date());
		floor.setLastEditTime(new Date());
		dao.insertFloor(floor);
	} 

	public List<FloorNotice> getNoticeFloor(PersonInfo user) {
		Floor floor = new Floor();
		floor.setUser(user);
		List<FloorNotice> list = dao.getFloorList(floor);
		Collections.sort(list, Comparator.comparing(FloorNotice::getCreateTime));
		Collections.reverse(list);
		return list;
	}
	
	public void lookOver(Integer floorId,PersonInfo user) {
		if(floorId == null)
			throw new LogicException("操作异常");
		if(user.getUserId() == dao.getDeployUser(floorId))
			dao.lookOver(floorId);
		else
			throw new LogicException("操作异常");
	}
	
	public void lookOverDelete(Integer floorId,PersonInfo user) {
		if(floorId == null)
			throw new LogicException("操作异常");
		if(user.getUserId() == dao.getDeployUser(floorId))
			dao.lookOverDelete(floorId);
		else
			throw new LogicException("操作异常");
	}
	
	public void lookOverDeleteAll(PersonInfo user) {
		if(user == null)
			throw new LogicException("未登录");
		dao.lookOverDeleteAll(user.getUserId());
	}
}
