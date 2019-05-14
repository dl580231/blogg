package com.nuc.a4q.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.a4q.dao.PostDao;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.UserRank;
import com.nuc.a4q.exception.LogicException;

@Service
public class PostService {
	@Autowired
	private PostDao dao;

	/**
	 * 删除帖子
	 * 
	 * @param post
	 * @throws Exception
	 */
	public void deletePost(Post post) {

	}

	/**
	 * 查询帖子信息
	 */
	public List<Post> queryPostList(Post post) {
		List<Post> list = dao.queryPostList(post);
		return list;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param postId
	 */
	public void removePost(Post post) {
		dao.deletePost(post);
	}

	/**
	 * 帖子置顶操作
	 * 
	 * @param post
	 */
	@Transactional
	public void topPost(Post post) {
		Integer minPriority = dao.queryMinPriority();
		post.setPriority(minPriority - 1);
		dao.updatePost(post);
	}

	/**
	 * 帖子置底操作
	 * 
	 * @param post
	 */
	@Transactional // 这里加上事务，解决不可重复读问题
	public void bottomPost(Post post) {
		Integer maxPriority = dao.queryMaxPriority();
		post.setPriority(maxPriority + 1);
		dao.updatePost(post);
	}

	/**
	 * 根据课程ID
	 * 
	 * @param courseId
	 */
	public Integer getPostCountByCourseId(Integer courseId) {
		return dao.getPostCountByCourseId(courseId);
	}

	/**
	 * 通过优先级获得已解决的问题列表
	 * 
	 * @return
	 */
	public HashMap<Object, Object> getResolvedPost(Integer rowStart,Integer rowSize,Integer courseId) {
		if(rowStart==null || rowSize==null)
			throw new LogicException("查询参数错误");
		Integer count = dao.queryPostCount(0);
		List<Post> list = dao.getResolvedPostTestByPriority(rowStart,rowSize,courseId);
		HashMap<Object, Object> map = new HashMap<Object,Object>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}

	/**
	 * 通过优先级获得未解决的问题列表
	 * 
	 * @return
	 */
	public HashMap<Object, Object> getUnResolvedPost(Integer rowStart,Integer rowSize,Integer courseId) {
		if(rowStart==null || rowSize==null)
			throw new LogicException("查询错误");
		Integer count = dao.queryPostCount(1);
		List<Post> list = dao.getUnResolvedPostTestByPriority(rowStart,rowSize,courseId);
		HashMap<Object, Object> map = new HashMap<Object,Object>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}

	/**
	 * 通过用户回答的问题的评价获得排序
	 * 
	 * @return
	 */
	public List<UserRank> getUserRank() {
		List<UserRank> userList = dao.getUserRank();
		return userList;
	}

	public List<Post> getResolved(Integer courseId, String postContent, String postTitle) {
		List<Post> list = dao.getResolvedByPriority(courseId, postContent, postTitle);
		return list;
	}

	public List<Post> getUnResolved(Integer courseId, String postContent, String postTitle) {
		List<Post> list = dao.getUnResolvedByPriority(courseId, postContent, postTitle);
		return list;
	}

	public Integer deployPost(Post post, Integer courseId, PersonInfo user) {
		if (courseId == null) {
			throw new LogicException("课程Id为空");
		}
		Course course = new Course();
		course.setCourseId(courseId);
		post.setCourse(course);
		post.setDeployUser(user);
		post.setCreateTime(new Date());
		post.setLastEditTime(new Date());
		dao.insertPost(post);
		return post.getPostId();
	}

	public Post getPostById(Integer postId) {
		Post post = dao.getPostById(postId);
		if (post == null) {
			throw new LogicException("帖子不存在");
		}
		return post;
	}

	/**
	 * 为帖子选取最佳答案
	 * 
	 * @param num
	 * @param user
	 * @param post
	 */
	public void electBestAnswer(Integer floorId, PersonInfo user, Post post) {
		// 1.判断空值
		if (floorId == null || user == null || post == null) {
			throw new LogicException("用户无操作权限");
		}
		// 2.判断权限
		if (post.getDeployUser().getUserId() == user.getUserId()) {
			post.setBestAnswerId(floorId);
			dao.updatePost(post);
		} else {
			throw new LogicException("用户无操作权限");
		}
	}
}
