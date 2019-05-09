package com.nuc.a4q.service;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.PageDivide;
import com.nuc.a4q.entity.PersonInfo;

public class PersonInfoServiceTest extends BaseTest {
	@Autowired
	private PersonInfoService service;
	private Logger logger = LoggerFactory.getLogger(PersonInfoServiceTest.class);

	@Test
	@Ignore
	public void loginAuthTest() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setPhone("18235105722");
		personInfo.setPassword("580231");
		PersonInfo loginAuth = service.loginAuth(personInfo);
		System.out.println(loginAuth);
	}

	@Test
	public void getPersonInfoListTest() {
		PageDivide pageDivide = new PageDivide();
		pageDivide.setCurrentPage(2);
		service.getPersonInfoList(pageDivide, null);
		logger.debug("" + pageDivide.getEntityList());
	}

}
