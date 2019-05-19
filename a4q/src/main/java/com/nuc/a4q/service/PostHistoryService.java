package com.nuc.a4q.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.PostHistoryDao;
import com.nuc.a4q.entity.PostHistory;

@Service
public class PostHistoryService {
	@Autowired
	PostHistoryDao dao;
	
	/**
	 * 增加浏览记录，
	 * 没有增加，存在则更新最后修改时间
	 * @param userId
	 * @param postId
	 */
	public void addPostHistory(Integer userId,Integer postId) {
		if(userId != null &&postId != null) {
			PostHistory history = new PostHistory();
			history.setPostId(postId);
			history.setUserId(userId);
			List<PostHistory> list = dao.getPostHistory(history);
			if(list.size() == 1) {
				history.setHistoryId(list.get(0).getHistoryId());
				history.setLastEditTime(new Date());
				dao.updatePostHistory(history);
			}else if(list.size() == 0) {
				history.setCreateTime(new Date());
				history.setLastEditTime(new Date());
				dao.insertHistory(history);
			}
		}
	}
}
