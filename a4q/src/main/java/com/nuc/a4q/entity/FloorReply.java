package com.nuc.a4q.entity;
/**
 * 
 * @author lenovo
 *
 *CREATE TABLE `tb_floor_reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `floor_id` int(11) NOT NULL,
  `reply_user_id` int(11) NOT NULL COMMENT '回答人id',
  `accept_user_id` int(11) NOT NULL COMMENT '被回复人id',
  `reply_content` varchar(1024) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `floor_id` (`floor_id`),
  KEY `reply_user_id` (`reply_user_id`),
  KEY `accept_user_id` (`accept_user_id`),
  CONSTRAINT `tb_floor_reply_ibfk_3` FOREIGN KEY (`accept_user_id`) REFERENCES `tb_floor` (`floor_id`),
  CONSTRAINT `tb_floor_reply_ibfk_1` FOREIGN KEY (`floor_id`) REFERENCES `tb_floor` (`floor_id`),
  CONSTRAINT `tb_floor_reply_ibfk_2` FOREIGN KEY (`reply_user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 */

import java.util.Date;

public class FloorReply {
	private Integer replyId;
	private Integer floorId;
	private PersonInfo replyUser;
	private PersonInfo acceptUser;
	private String replyContent;
	private Date createTime;

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public PersonInfo getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(PersonInfo replyUser) {
		this.replyUser = replyUser;
	}

	public PersonInfo getAcceptUser() {
		return acceptUser;
	}

	public void setAcceptUser(PersonInfo acceptUser) {
		this.acceptUser = acceptUser;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
