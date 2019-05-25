package com.nuc.a4q.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.a4q.dao.PersonInfoDao;
import com.nuc.a4q.dao.PostDao;
import com.nuc.a4q.dao.PostHistoryDao;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.PostHistory;
import com.nuc.a4q.entity.UserRank;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.recommend.ItemCF;

@Service
public class PostService {
	@Autowired
	private PostDao dao;
	@Autowired
	private ItemCF cf;
	@Autowired
	private PersonInfoDao userDao;
	@Autowired
	private PostHistoryDao historyDao;
	
	/**
	 * 删除帖子
	 * 
	 * @param post
	 * @throws Exception
	 */
	public void deletePost(Post post) {
		
	}

	/**
	 * 前台调用用查询帖子信息
	 */
	public List<Post> queryPostList(Post post) {
		List<Post> list = dao.queryPostList(post);
		Collections.sort(list, Comparator.comparing(Post::getCreateTime));
		Collections.reverse(list);
		return list;
	}
	
	public List<Post> queryPostListBack(Post post) {
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
		Collections.sort(userList, Comparator.comparing(UserRank::getNum));
		Collections.reverse(userList);
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
	
	/**
	 * 阅读量+1
	 * @param postId
	 * @return
	 */
	public Integer readCountAdd(Integer postId) {
		return dao.readCountAdd(postId);
	}
	
	/**
	 * 通过id
	 * @param postId
	 * @return
	 */
	public Integer getReadCountById(Integer postId) {
		if(postId==null)
			throw new LogicException("查询失败");
		return dao.getReadCountById(postId);
	}
	
	/**
	 * 获得阅读量排行
	 * @return
	 */
	public List<Post> getPostRankByReadCount(){
		List<Post> list = dao.getPostRankByReadCount();
		Collections.sort(list, Comparator.comparing(Post::getReadCount));
		Collections.reverse(list);
		return list;
	}
	
	public List<Post> getAnswerPost(Integer userId){
		if(userId == null)
			throw new LogicException("查询用户为空");
		List<Post> list = dao.getAnswerPost(userId);
		Collections.sort(list, Comparator.comparing(Post::getCreateTime));
		Collections.reverse(list);
		return list;
	}
	
	/**
	 * 逻辑删除帖子
	 * @param postId
	 */
	public void logicRmpost(Integer postId,PersonInfo user) {
		if(postId == null)
			throw new LogicException("查询为空");
		Post post = dao.getPostById(postId);
		if(user!=null && post!=null) {
			if(user.getUserId()!=null&&user.getUserId().equals(post.getDeployUser().getUserId())) {
				dao.logicRmpost(postId);
				return;
			}
		}
		throw new LogicException("无权操作");
	}
	
	/**
	 * 获取针对用户的推荐帖子
	 * @param userId
	 * @return
	 * @throws TasteException
	 */
	public List<Post> getRecommendPost(Integer userId) throws TasteException{
		PostHistory history = new PostHistory();
		history.setUserId(userId);
		List<PostHistory> historyDaoList = historyDao.getPostHistory(history);
		List<Integer> postIdList = new ArrayList<Integer>();
		if(historyDaoList.size() == 0) {
			/*------基于人口统计学的推荐算法解决冷启动问题开始-----*/
			PersonInfo requestUser = new PersonInfo();
			requestUser.setUserId(userId);
			PersonInfo user = userDao.queryPresonInfo(requestUser);
			requestUser.setUserId(null);
			requestUser.setGender(user.getGender());
			requestUser.setUserType(user.getUserType());
			List<Integer> userIdlist = dao.getUserIdInHistory(requestUser);
			for(Integer userIdP : userIdlist) {
				postIdList = cf.getRecommengBasedItemCF(userIdP,"tb_post_history","post_id");
				if(postIdList.size()>0) {
					break;
				}
			}
			if(postIdList.size() == 0)
				return null;
			/*------基于人口统计学的推荐算法解决冷启动问题结束-----*/
		}else {
			postIdList = cf.getRecommengBasedItemCF(userId,"tb_post_history","post_id");
		}
		LinkedList<Post> postList = new LinkedList<Post>();
		for(Integer postId : postIdList) {
			postList.add(dao.getPostById(postId));
		}
		return postList;
	}
}
