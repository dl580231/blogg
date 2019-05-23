package com.nuc.a4q.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.a4q.dao.BlogDao;
import com.nuc.a4q.dao.CourseDao;
import com.nuc.a4q.dao.PersonInfoDao;
import com.nuc.a4q.dto.BlogDto;
import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.exception.LogicException;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	
	public Integer addBlog(Blog blog,PersonInfo user) {
		blog.setUserId(user.getUserId());
		blog.setCreateTime(new Date());
		blog.setLastEditTime(new Date());
		blogDao.insert(blog);
		return blog.getBlogId();
	}
	
	public BlogDto getBlogById(Integer blogId) {
		if(blogId == null)
			throw new LogicException("blogId为空");
		Blog blog = blogDao.selectByPrimaryKey(blogId);
		Course course = new Course();
		course.setCourseId(blog.getCourseId());
		course = courseDao.queryCourseList(course).get(0);
		PersonInfo user = new PersonInfo();
		user.setUserId(blog.getUserId());
		user = personInfoDao.queryPresonInfo(user);
		BlogDto blogDto = new BlogDto();
		blogDto.setBlog(blog);
		blogDto.setCourse(course);
		blogDto.setUser(user);
		return blogDto;
	}

	public void readCountAdd(Integer blogId) {
		blogDao.readCountAdd(blogId);
	}
}
