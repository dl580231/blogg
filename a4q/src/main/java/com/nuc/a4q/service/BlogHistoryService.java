package com.nuc.a4q.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.BlogHistoryDao;
import com.nuc.a4q.entity.BlogHistory;

@Service
public class BlogHistoryService {
	@Autowired
	private BlogHistoryDao dao;
	
	/**
	 * 增加浏览记录，
	 * 没有增加，存在则更新最后修改时间
	 * @param userId
	 * @param blogId
	 */
	public void addBlogHistory(Integer userId,Integer blogId) {
		if(userId != null &&blogId != null) {
			BlogHistory history = new BlogHistory();
			history.setBlogId(blogId);
			history.setUserId(userId);
			List<BlogHistory> list = dao.getBlogHistory(history);
			if(list.size() == 1) {
				history.setHistoryId(list.get(0).getHistoryId());
				history.setLastEditTime(new Date());
				dao.updateByPrimaryKeySelective(history);
			}else if(list.size() == 0) {
				history.setCreateTime(new Date());
				history.setLastEditTime(new Date());
				dao.insertSelective(history);
			}
		}
	}
}
