package com.nuc.a4q.entity;
/**
 * 
 * @author DL
 *CREATE TABLE `tb_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `best_answer_id` int(11) DEFAULT NULL,
  `post_content` varchar(1024) NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `enable_state` int(2) DEFAULT '1' COMMENT '是否可见 0：不可见 1：可见',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `course_id` (`course_id`,`best_answer_id`),
  KEY `user_id` (`user_id`),
  KEY `best_answer_id` (`best_answer_id`),
  CONSTRAINT `tb_post_ibfk_3` FOREIGN KEY (`best_answer_id`) REFERENCES `tb_floor` (`floor_id`),
  CONSTRAINT `tb_post_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `tb_course` (`course_id`),
  CONSTRAINT `tb_post_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 */

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.nuc.a4q.group.Delete;
import com.nuc.a4q.group.Insert;
import com.nuc.a4q.group.Update;

public class Post {
	@NotNull(message = "帖子ID", groups = { Delete.class, Update.class })
	private Integer postId;
	private Course course;
	private PersonInfo deployUser;
	private Integer bestAnswerId;
	@NotBlank(message="发帖标题不能为空",groups= {Insert.class})
	private String postTitle;
	@NotBlank(message="发帖内容不能为空",groups= {Insert.class})
	private String postContent;
	private Integer priority;
	/**
	 * 0:可见，1：不可见
	 */
	private Integer enableView;
	private Date createTime;
	private Date lastEditTime;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public PersonInfo getDeployUser() {
		return deployUser;
	}

	public void setDeployUser(PersonInfo deployUser) {
		this.deployUser = deployUser;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
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

	public Integer getBestAnswerId() {
		return bestAnswerId;
	}

	public void setBestAnswerId(Integer bestAnswerId) {
		this.bestAnswerId = bestAnswerId;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", course=" + course + ", deployUser=" + deployUser + ", bestAnswerId="
				+ bestAnswerId + ", postTitle=" + postTitle + ", postContent=" + postContent + ", priority=" + priority
				+ ", enableView=" + enableView + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
