package com.nuc.a4q.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest {
	@Autowired
	private PersonInfoDao dao;

	@Test
	@Ignore
	public void insertPersonInfoTest() {
		PersonInfo info = new PersonInfo();
		info.setUserName("王丹丹");
		info.setGender("女");
		info.setUserType("学生");
		info.setEmail("9577938035@qq.com");
		info.setPhone("150345939083");
		info.setPassword("580231");
		/* info.setUserType(0); */
		info.setCreateTime(new Date());
		info.setLastEditTime(new Date());
		Integer i = dao.insertPersonInfo(info);
		System.out.println(i);
	}
	
	@Test
	@Ignore
	public void deleteUserTest() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(21);
		Integer result = dao.deleteUser(personInfo);
		System.out.println(result);
	}


	@Test
	@Ignore
	public void getPresonInfoListTest() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserType("学生");
		personInfo.setGender("男");
		personInfo.setPhone("18235105722");
		personInfo.setEmail("");
		List<PersonInfo> list = dao.queryPersonInfoList(0, 1, personInfo);
		System.out.println(list);
	}

	@Test
	@Ignore
	public void getPersonInfoCountTest() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserType("学生");
		personInfo.setGender("男");
		personInfo.setPhone("18235105722");
		Integer personInfoCount = dao.queryPersonInfoCount(personInfo);
		System.out.println(personInfoCount);
	}
	
	@Test
	@Ignore
	public void queryPresonInfo() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1);
		PersonInfo result = dao.queryPresonInfo(personInfo);
		System.out.println(result);
	}
	
	@Test
	public void updateUser() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1);
		personInfo.setUserName("三十");
		personInfo.setGender("男");
		personInfo.setLastEditTime(new Date());
		Integer result = dao.updateUser(personInfo);
		System.out.println(result);
	}
}
