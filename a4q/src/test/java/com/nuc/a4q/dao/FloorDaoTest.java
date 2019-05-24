package com.nuc.a4q.dao;

import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.FloorNotice;
import com.nuc.a4q.entity.PersonInfo;

public class FloorDaoTest extends BaseTest {
	@Autowired
	private FloorDao dao;

	@Test
	@Ignore
	public void queryFloorListTest() {
		Floor floor = new Floor();
		floor.setFloorContent("d");
		List<Floor> floorList = dao.queryFloorList(floor);
		System.out.println(floorList);
	}

	@Test
	@Ignore
	public void insertFloor() {
		Floor floor = new Floor();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(16);
		floor.setUser(personInfo);
		floor.setPostId(13);
		floor.setFloorContent("JavaJavaJavaJavaJavaJavaJavaJavaJavaJavaJavaJavaJava");
		floor.setCreateTime(new Date());
		floor.setLastEditTime(new Date());
		Integer insertFloor = dao.insertFloor(floor);
		System.out.println(insertFloor);
	}
	
	@Test
	@Ignore
	public void deleteFloor() {
		Floor floor = new Floor();
		floor.setFloorId(11);
		Integer result = dao.deleteFloor(floor);
		System.out.println(result);
	}
	
	@Test
	@Ignore
	public void lookOverDelete() {
		dao.lookOverDelete(8);
	}
	
	@Test
//	@Ignore
	public void getFloorList() {
		Floor floor = new Floor();
		PersonInfo user = new PersonInfo();
		user.setUserId(1);
		floor.setUser(user);
		floor.setLookOver(0);
		List<FloorNotice> list = dao.getFloorList(floor);
		System.out.println(list.size());
	}
	
	@Test
	@Ignore
	public void getDeployUser() {
		Integer deployUser = dao.getDeployUser(8);
		System.out.println(deployUser);
	}
}
