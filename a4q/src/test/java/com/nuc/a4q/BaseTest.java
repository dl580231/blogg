package com.nuc.a4q;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置Spring与Junit整合，Junit启动时加载spring容器
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations= {"classpath:/spring/spring-dao.xml"})*/
@ContextConfiguration(locations= {"classpath:/spring/spring-dao.xml","classpath:/spring/spring-service.xml"})
/*@ContextConfiguration(locations= {"classpath:/spring/spring-*.xml"})*/
public class BaseTest {

}
