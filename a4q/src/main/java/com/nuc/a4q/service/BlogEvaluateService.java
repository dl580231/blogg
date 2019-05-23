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

	public List<BlogEvaluateDto> getEvaluateList(Blog blog, BlogEvaluate evaluate) {
		evaluate.setBlogId(blog.getBlogId());
		List<BlogEvaluateDto> list = dao.getEvaluateList(evaluate);
		Collections.sort(list, Comparator.comparing(BlogEvaluateDto::getCreateTime));
		return list;
	}
}
