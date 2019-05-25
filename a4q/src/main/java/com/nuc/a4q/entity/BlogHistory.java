package com.nuc.a4q.entity;

import java.util.Date;

public class BlogHistory {
    private Integer historyId;

    private Integer blogId;

    private Integer userId;

    private Float preference;

    private Date createTime;

    private Date lastEditTime;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getPreference() {
        return preference;
    }

    public void setPreference(Float preference) {
        this.preference = preference;
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
		return "BlogHistory [historyId=" + historyId + ", blogId=" + blogId + ", userId=" + userId + ", preference="
				+ preference + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}
    
}