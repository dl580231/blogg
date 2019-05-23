package com.nuc.a4q.dto;

import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;

public class BlogDto {
	private Blog blog;
	private Course course;
	private PersonInfo user;
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public PersonInfo getUser() {
		return user;
	}
	public void setUser(PersonInfo user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "BlogDto [blog=" + blog + ", course=" + course + ", user=" + user + "]";
	}
}
