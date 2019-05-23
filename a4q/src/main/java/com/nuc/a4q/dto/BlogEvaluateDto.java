package com.nuc.a4q.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.group.Insert;

public class BlogEvaluateDto {
    private Integer evaluateId;

    private Blog blog;

    private PersonInfo user;

    @NotBlank(message="发布内容不能为空",groups= {Insert.class})
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

    public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public PersonInfo getUser() {
		return user;
	}

	public void setUser(PersonInfo user) {
		this.user = user;
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
		return "BlogEvaluateDto [evaluateId=" + evaluateId + ", blog=" + blog + ", user=" + user + ", evaluateContent="
				+ evaluateContent + ", lookOver=" + lookOver + ", lookOverDelete=" + lookOverDelete + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + "]";
	}
    	
    
}