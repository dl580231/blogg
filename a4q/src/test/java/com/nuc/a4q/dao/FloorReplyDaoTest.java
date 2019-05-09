package com.nuc.a4q.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.FloorReply;
import com.nuc.a4q.entity.PersonInfo;

public class FloorReplyDaoTest extends BaseTest {
	@Autowired
	private FloorReplyDao dao;

	@Test
	@Ignore
	public void insertFlooReplyTest() {
		PersonInfo replyUser = new PersonInfo();
		replyUser.setUserId(1);
		PersonInfo acceptUser = new PersonInfo();
		acceptUser.setUserId(3);
		FloorReply floorReply = new FloorReply();
		floorReply.setAcceptUser(acceptUser);
		floorReply.setReplyUser(replyUser);
		floorReply.setFloorId(2);
		floorReply.setReplyContent("dmeodemodmeo");
		floorReply.setCreateTime(new Date());
		Integer result = dao.insertFloorReply(floorReply);
		System.out.println(result);
	}

	@Test
	public void deleteFloorReply() {
		FloorReply floorReply = new FloorReply();
		floorReply.setReplyContent("remove");
		Integer result = dao.deleteFloorReply(floorReply);
		System.out.println(result);
	}
}
