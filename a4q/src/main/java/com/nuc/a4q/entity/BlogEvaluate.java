package com.nuc.a4q.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.nuc.a4q.group.Insert;

public class BlogEvaluate {
    private Integer evaluateId;

    private Integer blogId;

    private Integer userId;

    @NotBlank(message="评论内容不能为空",groups= {Insert.class})
    private String evaluateContent;

    private Integer lookOver;
    
    private Integer lookOverDelete;

	private Date createTime;

    private Date lastEditTime;

    public Integer getLookOver() {
		return lookOver;
	}

	public void setLookOver(Integer lookOver) {
		this.lookOver = lookOver;
	}

	public Integer getLookOverDelete() {
		return lookOverDelete;
	}

	public void setLookOverDelete(Integer lookOverDelete) {
		this.lookOverDelete = lookOverDelete;
	}
	
    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
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

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
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
		return "BlogEvaluate [evaluateId=" + evaluateId + ", blogId=" + blogId + ", userId=" + userId
				+ ", evaluateContent=" + evaluateContent + ", lookOver=" + lookOver + ", lookOverDelete="
				+ lookOverDelete + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}
    
}