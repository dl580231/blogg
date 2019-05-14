package com.nuc.a4q.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.UserRank;

public class PostDaoTest extends BaseTest {
	@Autowired
	private PostDao postdao;

	@Test
	@Ignore
	public void insertPostTest() {
		Post post = new Post();
		Course course = new Course();;
		course.setCourseId(3);
		post.setCourse(course);
		PersonInfo per = new PersonInfo();
		per.setUserId(1);
		post.setDeployUser(per);
		post.setPostTitle("这是一个测试的帖子标题");
		post.setPostContent("这是一个测试的帖子标content");
		post.setPriority(null);
		post.setEnableView(null);
		post.setCreateTime(new Date());
		post.setLastEditTime(new Date());
		Integer integer = postdao.insertPost(post);
		System.out.println(integer);
	}

	@Test
	@Ignore
	public void updatepostTest() {
		Post post = new Post();
		post.setPostId(1);
		Course course = new Course();
		course.setCourseId(16);
		post.setCourse(course);
		PersonInfo per = new PersonInfo();
		per.setUserId(3);
		post.setDeployUser(per);
		post.setPostTitle("这是一个问题");
		post.setPostContent("这是一个测试的内容");
		post.setPriority(1);
		post.setEnableView(0);
		post.setCreateTime(new Date());
		post.setLastEditTime(new Date());
		Integer integer = postdao.updatePost(post);
		System.out.println(integer);
	}

	@Test
	@Ignore
	public void deletePostTest() {
		Post post = new Post();
		post.setPostId(2);
		Integer integer = postdao.deletePost(post);
		System.out.println(integer);
	}

	@Test
	@Ignore
	public void queryPostListTest() {
		Post post = new Post();
		List<Post> list = postdao.queryPostList(post);
		System.out.println(list);
	}

	@Test
	public void queryPostCountTest() {
		System.out.println(postdao.queryPostCount(1));
	}

	@Test
	@Ignore
	public void queryPostPageList() {
		Post post = new Post();
		post.setPostTitle("c++");
		List<Post> list = postdao.queryPostPageList(0, 3, post);
		System.out.println(list);
	}
	
	@Test
	@Ignore
	public void queryMinPriorityTest() {
		Integer minPriority = postdao.queryMinPriority();
		System.out.println(minPriority);
	}
	
	@Test
	@Ignore
	public void getResolvedPostTestByPriority() {
		List<Post> list = postdao.getResolvedPostTestByPriority(0,2,3);
		System.out.println(list);
	}

	@Test
	@Ignore
	public void getUnResolvedPostTestByPriority() {
		List<Post> list = postdao.getUnResolvedPostTestByPriority(0,2,3);
		System.out.println(list);
	}
	
	@Test
	@Ignore
	public void getUserRankTest() {
		List<UserRank> list = postdao.getUserRank();
		System.out.println(list);
	}
	
	@Test
	@Ignore
	public void getResolvedByPriority() {
		List<Post> list = postdao.getResolvedByPriority(4,"","");
		System.out.println(list);
	}
	
	@Test
	@Ignore
	public void getUnResolvedByPriority() {
		List<Post> list = postdao.getUnResolvedByPriority(null,"","网络");
		System.out.println(list);
	}
	
	@Test
	@Ignore
	public void getPostById() {
		Post post = postdao.getPostById(1);
		System.out.println(post);
	}
}
