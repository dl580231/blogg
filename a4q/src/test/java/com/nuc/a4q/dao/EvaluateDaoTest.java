package com.nuc.a4q.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Evaluate;

public class EvaluateDaoTest extends BaseTest {
	@Autowired
	private EvaluateDao dao;

	@Test
	@Ignore
	public void insertEvaluate() {
		Evaluate evaluate = new Evaluate();
		evaluate.setPostId(1);
		evaluate.setUserId(3);
		evaluate.setCreateTime(new Date());
		Integer result = dao.insertEvaluate(evaluate);
		System.out.println(result);
	}

	@Test
	public void deleteEvaluate() {
		Evaluate evaluate = new Evaluate();
		evaluate.setUserId(3);
		Integer result = dao.deleteEvaluate(evaluate);
		System.out.println(result);
	}
}
