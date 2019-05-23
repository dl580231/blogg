package com.nuc.a4q.dao;

import java.util.List;

import com.nuc.a4q.entity.BlogHistory;

public interface BlogHistoryDao {
    int deleteByPrimaryKey(Integer historyId);

    int insert(BlogHistory record);

    int insertSelective(BlogHistory record);

    BlogHistory selectByPrimaryKey(Integer historyId);

    int updateByPrimaryKeySelective(BlogHistory record);
    
    int updateByPrimaryKey(BlogHistory record);
    
    public List<BlogHistory> getBlogHistory(BlogHistory history);
}