package com.nuc.a4q.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.PostHistory;

public class PostHistoryDaoTest extends BaseTest {
	@Autowired
	PostHistoryDao dao;
	
	@Test
	@Ignore
	public void insertHistoryTest() {
		PostHistory history = new PostHistory();
		history.setPostId(100);
		history.setUserId(100);
		history.setCreateTime(new Date());
		history.setLastEditTime(new Date());
		Integer result = dao.insertHistory(history);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void getPostHistory() {
		PostHistory history = new PostHistory();
		history.setUserId(1);
		List<PostHistory> list = dao.getPostHistory(history);
		System.out.println(list);
	}
	
	@Test
	public void updatePostHistoryTest() {
		PostHistory history = new PostHistory();
		history.setHistoryId(1);
		history.setLastEditTime(new Date());
		Integer result = dao.updatePostHistory(history);
		System.out.println(result);
	}
}
