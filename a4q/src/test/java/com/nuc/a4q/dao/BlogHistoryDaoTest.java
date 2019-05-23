package com.nuc.a4q.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.BlogHistory;

public class BlogHistoryDaoTest extends BaseTest {
	@Autowired
	private BlogHistoryDao dao;
	
	@Test
	public void getBlogHistory() {
		BlogHistory history = new BlogHistory();
		history.setBlogId(1);
		history.setUserId(1);
		List<BlogHistory> list = dao.getBlogHistory(history);
		System.out.println(list);
	}
}
