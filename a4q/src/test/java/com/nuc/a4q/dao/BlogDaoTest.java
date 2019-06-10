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

import cn.hutool.crypto.digest.DigestUtil;

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
//	@Ignore
	public void queryBlogCount() {
		Integer count = dao.queryBlogCount(19);
		System.out.println(count);
	}
	
	@Test
	@Ignore
	public void getBlogOrderByPriority() {
		Blog blog = new Blog();
		blog.setBlogContent("java");
		List<BlogDto> list = dao.getBlogOrderByPriority(null, null, blog);
		System.out.println(list.size());
	}
	
	@Test
	@Ignore
	public void test() {
		String md5Hex1 = DigestUtil.md5Hex("580231");
		System.out.println(md5Hex1);
	}
}
