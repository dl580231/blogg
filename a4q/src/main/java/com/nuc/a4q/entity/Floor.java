package com.nuc.a4q.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.nuc.a4q.group.Insert;

/**
 * 
 * @author lenovo
 *
 *         CREATE TABLE `tb_floor` ( `floor_id` int(11) NOT NULL AUTO_INCREMENT,
 *         `post_id` int(11) NOT NULL, `user_id` int(11) NOT NULL,
 *         `floor_content` varchar(1024) NOT NULL, `create_time` datetime
 *         DEFAULT NULL, `last_edit_time` datetime DEFAULT NULL, PRIMARY KEY
 *         (`floor_id`), KEY `post_id` (`post_id`), KEY `user_id` (`user_id`),
 *         CONSTRAINT `tb_floor_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES
 *         `tb_person_info` (`user_id`), CONSTRAINT `tb_floor_ibfk_1` FOREIGN
 *         KEY (`post_id`) REFERENCES `tb_post` (`post_id`) ) ENGINE=InnoDB
 *         DEFAULT CHARSET=utf8;
 * 
 * 
 */
public class Floor {
	@NotNull(message="楼ID不能为空",groups= {})
	private Integer floorId;
	@NotNull(message="帖子ID为空",groups= {})
	private Integer postId;
	private PersonInfo user;
	private Integer lookOver;
	private Integer lookOverDelete;
	private Date createTime;
	private Date lastEditTime;
	@NotBlank(message="回答内容不能为空",groups= {Insert.class})
	private String floorContent;

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

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

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public PersonInfo getUser() {
		return user;
	}

	public void setUser(PersonInfo user) {
		this.user = user;
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

	public String getFloorContent() {
		return floorContent;
	}

	public void setFloorContent(String floorContent) {
		this.floorContent = floorContent;
	}

	@Override
	public String toString() {
		return "Floor [floorId=" + floorId + ", postId=" + postId + ", user=" + user + ", lookOver=" + lookOver
				+ ", lookOverDelete=" + lookOverDelete + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime
				+ ", floorContent=" + floorContent + "]";
	}

	

}
