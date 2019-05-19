package com.nuc.a4q.entity;

import java.util.Date;

public class PostHistory {
	private Integer historyId;
	private Integer postId;
	private Integer userId;
	private Date createTime;
	private Date lastEditTime;
	
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
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
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
	@Override
	public String toString() {
		return "PostHistory [historyId=" + historyId + ", postId=" + postId + ", userId=" + userId + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + "]";
	}
}
