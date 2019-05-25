package com.nuc.a4q.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nuc.a4q.dto.BlogDto;
import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.UserRank;

public interface BlogDao {
    int deleteByPrimaryKey(Integer blogId);

    public Integer insert(Blog blog);

    public Integer insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer blogId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    public Integer readCountAdd(Integer blogId);
    
    public List<Blog> getBlogList(Blog blog);

	public List<UserRank> getUserRank();

	public List<Blog> getBlogRankByReadCount();

	public Integer queryBlogCount();

	public List<BlogDto> getBlogOrderByPriority(@Param("rowStart") Integer rowStart,
			@Param("rowSize") Integer rowSize, @Param("blog") Blog blog);

	public List<BlogDto> getSerachBlog(String blogContent, String blogTitle);

	

}