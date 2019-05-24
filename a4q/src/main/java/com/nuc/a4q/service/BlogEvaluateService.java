package com.nuc.a4q.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.BlogEvaluateDao;
import com.nuc.a4q.dto.BlogEvaluateDto;
import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.BlogEvaluate;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.exception.LogicException;

@Service
public class BlogEvaluateService {
	@Autowired
	private BlogEvaluateDao dao;
	
	public void addEvaluate(PersonInfo user,BlogEvaluate evaluate,Blog blog) {
		evaluate.setBlogId(blog.getBlogId());
		evaluate.setUserId(user.getUserId());
		evaluate.setCreateTime(new Date());
		evaluate.setLastEditTime(new Date());
		dao.insertSelective(evaluate);
	}

	public List<BlogEvaluateDto> getEvaluateList(Blog blog,BlogEvaluate evaluate) {
		evaluate.setBlogId(blog.getBlogId());
		List<BlogEvaluateDto> list = dao.getEvaluateList(evaluate);
		Collections.sort(list, Comparator.comparing(BlogEvaluateDto::getCreateTime));
		return list;
	}

	public List<BlogEvaluateDto> getNoticeEvaluate(PersonInfo user) {
		BlogEvaluate evaluate = new BlogEvaluate();
		evaluate.setUserId(user.getUserId());
		List<BlogEvaluateDto> list = dao.getEvaluateNotice(evaluate);
		Collections.sort(list, Comparator.comparing(BlogEvaluateDto::getCreateTime));
		Collections.reverse(list);
		return list;
	}

	public void lookOver(Integer evaluateId, PersonInfo user) {
		if(evaluateId == null)
			throw new LogicException("操作异常");
		if(user.getUserId() == dao.getDeployUser(evaluateId)) {
			BlogEvaluate evaluate = new BlogEvaluate();
			evaluate.setEvaluateId(evaluateId);
			evaluate.setLookOver(1);
			dao.updateByPrimaryKeySelective(evaluate);
		}
		else
			throw new LogicException("操作异常");
	}
	
	public void lookOverDelete(Integer evaluateId,PersonInfo user) {
		if(evaluateId == null)
			throw new LogicException("操作异常");
		if(user.getUserId() == dao.getDeployUser(evaluateId)) {
			BlogEvaluate evaluate = new BlogEvaluate();
			evaluate.setEvaluateId(evaluateId);
			evaluate.setLookOverDelete(1);
			dao.updateByPrimaryKeySelective(evaluate);
		} else
			throw new LogicException("操作异常");
	}
	
	public void lookOverDeleteAll(PersonInfo user) {
		if(user == null)
			throw new LogicException("未登录");
		dao.lookOverDeleteAll(user.getUserId());
	}
}
