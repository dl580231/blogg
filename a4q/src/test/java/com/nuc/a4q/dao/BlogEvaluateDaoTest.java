package com.nuc.a4q.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.dto.BlogEvaluateDto;
import com.nuc.a4q.entity.BlogEvaluate;

public class BlogEvaluateDaoTest extends BaseTest {
	@Autowired
	private BlogEvaluateDao dao;
	
	@Test
	@Ignore
	public void getEvaluateList() {
		BlogEvaluate evaluate = new BlogEvaluate();
		evaluate.setBlogId(1);
		List<BlogEvaluateDto> list = dao.getEvaluateList(evaluate);
		System.out.println(list);
	}
	
	@Test
//	@Ignore
	public void getEvaluateNotice() {
		BlogEvaluate evaluate = new BlogEvaluate();
		evaluate.setUserId(1);
		evaluate.setLookOver(0);
		dao.getEvaluateNotice(evaluate);
	}
	
	@Test
	@Ignore
	public void queryEvaluateList() {
		BlogEvaluate evaluate = new BlogEvaluate();
		evaluate.setUserId(1);
		evaluate.setLookOver(0);
		evaluate.setLookOverDelete(0);
		List<BlogEvaluate> list = dao.queryEvaluateList(evaluate);
		System.out.println(list.size());
	}
	
	@Test
	@Ignore
	public void getDeployUser() {
		Integer deployUser = dao.getDeployUser(2);
		System.out.println(deployUser);
	}
}
