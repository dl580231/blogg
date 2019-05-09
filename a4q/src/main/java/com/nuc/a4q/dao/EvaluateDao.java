package com.nuc.a4q.dao;

import com.nuc.a4q.entity.Evaluate;

public interface EvaluateDao {
	/**
	 * 增加评价，这里的评价是当回答设置为最终答案是则增加一条评价记录，一个帖子一个最佳答案
	 * 
	 * @param evaluate
	 * @return
	 */
	public Integer insertEvaluate(Evaluate evaluate);
	
	public Integer deleteEvaluate(Evaluate evaluate);
}
