package com.nuc.a4q.dao;

import com.nuc.a4q.entity.Blog;

public interface BlogDao {
    int deleteByPrimaryKey(Integer blogId);

    public Integer insert(Blog blog);

    public Integer insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer blogId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    public Integer readCountAdd(Integer blogId);
}