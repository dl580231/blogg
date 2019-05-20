package com.nuc.a4q.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.UserRank;

public interface PostDao {
	/**
	 * 增加帖子
	 * 
	 * @return
	 */
	public Integer insertPost(Post post);

	/**
	 * 修改帖子
	 * 
	 * @param course
	 * @return
	 */
	public Integer updatePost(Post post);

	/**
	 * 删除帖子
	 * 
	 * @param course
	 * @return
	 */
	public Integer deletePost(Post post);

	/**
	 * 模糊查询
	 */
	public List<Post> queryPostList(Post post);

	/**
	 * 获取总量
	 */
	public Integer queryPostCount(@Param(value = "resolve") Integer resolve);

	/**
	 * 分页查询
	 */
	public List<Post> queryPostPageList(@Param(value = "rowStart") Integer rowStart,
			@Param(value = "rowSize") Integer rowSize, @Param("post") Post post);

	/**
	 * 查询最小priority
	 * 
	 * @return
	 */
	public Integer queryMinPriority();

	/**
	 * 查询最小priority
	 * 
	 * @return
	 */
	public Integer queryMaxPriority();

	/**
	 * 根据courseId获得post的数量
	 * 
	 * @param courseId
	 * @return
	 */
	public Integer getPostCountByCourseId(Integer courseId);

	/**
	 * 通过优先级获得已解决问题的列表
	 */
	public List<Post> getResolvedPostTestByPriority(@Param("rowStart") Integer rowStart,
			@Param("rowSize") Integer rowSize, @Param("courseId") Integer course);

	/**
	 * 通过优先级获得未解决问题的列表
	 * 
	 * @return
	 */
	public List<Post> getUnResolvedPostTestByPriority(@Param("rowStart") Integer rowStart,
			@Param("rowSize") Integer rowSize, @Param("courseId") Integer course);

	/**
	 * 获取用户回答排行榜
	 * 
	 * @return
	 */
	public List<UserRank> getUserRank();

	public List<Post> getResolvedByPriority(@Param("courseId") Integer courseId,
			@Param("postContent") String postContent, @Param("postTitle") String postTitle);

	/**
	 * 通过优先级获得未解决问题的列表
	 * 
	 * @return
	 */
	public List<Post> getUnResolvedByPriority(@Param("courseId") Integer courseId,
			@Param("postContent") String postContent, @Param("postTitle") String postTitle);

	/**
	 * 通过postId获得post
	 * @param postId
	 * @return
	 */
	public Post getPostById(Integer postId);
	
	/**
	 * 阅读量+1
	 * @param postId
	 * @return
	 */
	public Integer readCountAdd(Integer postId);
	
	/**
	 * 获得阅读量
	 * @return
	 */
	public Integer getReadCountById(Integer postId);
	
	/**
	 * 根据阅读量获得文章排名
	 * @return
	 */
	public List<Post> getPostRankByReadCount();
	
	/**
	 * 获得回答过帖子的列表
	 * @param userId
	 * @return
	 */
	public List<Post> getAnswerPost(Integer userId);
	
	/**
	 * 逻辑删除帖子
	 * @param postId
	 * @return
	 */
	public Integer logicRmpost(Integer postId);
	
	/**
	 * 基于人口统计学的推荐，
	 * 基于用户属性获得相似用户
	 * @param user
	 * @return
	 */
	public List<Integer> getUserIdInHistory(PersonInfo user);
}
