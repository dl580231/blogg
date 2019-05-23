package com.nuc.a4q.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Blog;

public class BlogDaoTest extends BaseTest {
	@Autowired
	BlogDao dao;
	
	@Test
	@Ignore
	public void addBlog() {
		Blog blog = new Blog();
		blog.setUserId(1);
		blog.setBlogTitle("demo");
		blog.setBlogContent("demo");
		blog.setCreateTime(new Date());
		blog.setLastEditTime(new Date());
		blog.setCourseId(3);
		System.out.println(blog.getBlogId());
	}
}
