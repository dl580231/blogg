package com.nuc.a4q.entity;

import java.util.Date;

/**
 * 
 * @author lenovo
 *
 *         CREATE TABLE `tb_evaluate` ( `evaluate_id` int(11) NOT NULL
 *         AUTO_INCREMENT, `post_id` int(11) NOT NULL, `user_id` int(11) NOT
 *         NULL, `create_time` datetime DEFAULT NULL, PRIMARY KEY
 *         (`evaluate_id`), KEY `post_id` (`post_id`), KEY `user_id`
 *         (`user_id`), CONSTRAINT `tb_evaluate_ibfk_2` FOREIGN KEY (`user_id`)
 *         REFERENCES `tb_person_info` (`user_id`), CONSTRAINT
 *         `tb_evaluate_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `tb_post`
 *         (`post_id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 
 * 
 */
public class Evaluate {
	private Integer evaluateId;
	private Integer postId;
	private Integer userId;
	private Date createTime;

	public Integer getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
