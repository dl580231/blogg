package com.nuc.a4q.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.nuc.a4q.group.Insert;
import com.nuc.a4q.group.Update;

public class Blog {
	@NotNull(message="修改博客ID不能为空",groups= {Update.class})
    private Integer blogId;

    @NotNull(message="发布博客分类不能为空",groups= {Insert.class,Update.class})
    private Integer courseId;

    private Integer userId;

    @NotBlank(message="博客题目不能为空",groups= {Insert.class,Update.class})
    private String blogTitle;
    
    @NotBlank(message="博客内容不能为空",groups= {Insert.class,Update.class})
    private String blogContent;

    private Integer priority;

    private Integer enableView;

    private Integer readCount;

    private Date createTime;

    private Date lastEditTime;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent == null ? null : blogContent.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEnableView() {
        return enableView;
    }

    public void setEnableView(Integer enableView) {
        this.enableView = enableView;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
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
		return "Blog [blogId=" + blogId + ", courseId=" + courseId + ", userId=" + userId + ", blogTitle=" + blogTitle
				+ ", blogContent=" + blogContent + ", priority=" + priority + ", enableView=" + enableView
				+ ", readCount=" + readCount + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}
    
    
}