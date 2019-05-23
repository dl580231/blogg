package com.nuc.a4q.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;

public class BlogHistoryServiceTest extends BaseTest {
	@Autowired
	private BlogHistoryService service;
	
	@Test
	public void addBlogHistory() {
		service.addBlogHistory(1, 1);
	}
}
