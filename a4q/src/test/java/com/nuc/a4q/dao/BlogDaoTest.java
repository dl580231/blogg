package com.nuc.a4q.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.dto.BlogDto;
import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.UserRank;

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
	
	@Test
	@Ignore
	public void getBlogList() {
		Blog blog = new Blog();
		blog.setUserId(1);
		List<Blog> list = dao.getBlogList(blog);
		System.out.println(list.size());
	}
	
	@Test
	@Ignore
	public void getUserRank() {
		List<UserRank> list = dao.getUserRank();
		System.out.println(list);
	}
	
	@Test
	@Ignore
	public void getPostRankByReadCount() {
		List<Blog> list = dao.getBlogRankByReadCount();
		System.out.println(list);
	}
	
	@Test
	@Ignore
	public void queryBlogCount() {
		Integer count = dao.queryBlogCount();
		System.out.println(count);
	}
	
	@Test
	public void getBlogOrderByPriority() {
		List<BlogDto> list = dao.getBlogOrderByPriority(0, 5, 3);
		System.out.println(list);
	}
}
