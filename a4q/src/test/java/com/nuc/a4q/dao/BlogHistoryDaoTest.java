package com.nuc.a4q.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.BlogHistory;
import com.nuc.a4q.entity.PersonInfo;

public class BlogHistoryDaoTest extends BaseTest {
	@Autowired
	private BlogHistoryDao dao;
	
	@Test
	@Ignore
	public void getBlogHistory() {
		BlogHistory history = new BlogHistory();
		history.setBlogId(1);
		history.setUserId(1);
		List<BlogHistory> list = dao.getBlogHistory(history);
		System.out.println(list);
	}
	
	@Test
	public void getUserIdInHistoryTest() {
		PersonInfo user = new PersonInfo();
		user.setUserType("老师");
		user.setGender("女");
		List<Integer> list = dao.getUserIdInHistory(user);
		System.out.println(list);
	}
}
