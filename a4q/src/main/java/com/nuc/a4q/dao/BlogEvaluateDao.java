package com.nuc.a4q.dao;

import java.util.List;

import com.nuc.a4q.dto.BlogEvaluateDto;
import com.nuc.a4q.entity.BlogEvaluate;

public interface BlogEvaluateDao {
    int deleteByPrimaryKey(Integer evaluateId);

    int insert(BlogEvaluate record);

    int insertSelective(BlogEvaluate record);

    BlogEvaluate selectByPrimaryKey(Integer evaluateId);

    int updateByPrimaryKeySelective(BlogEvaluate record);

    int updateByPrimaryKey(BlogEvaluate record);
    
    /**
     * 获取评论列表
     * @param evaluate
     * @return
     */
    List<BlogEvaluateDto> getEvaluateList(BlogEvaluate evaluate);

    /**
     * 获得博客的通知
     * @param userId
     * @return
     */
	public List<BlogEvaluateDto> getEvaluateNotice(Integer userId); 
	
	public Integer getDeployUser(Integer evaluateId);

	public Integer lookOverDeleteAll(Integer userId);
}