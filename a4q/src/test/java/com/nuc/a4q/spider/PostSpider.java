package com.nuc.a4q.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.service.FloorService;
import com.nuc.a4q.service.PostService;


public class PostSpider extends BaseTest {
	@Autowired
	PostService service;
	@Autowired
	FloorService floorService;
	
	ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,3,4,9,14,15,16,17,18,19,20,26,27,28));
	
	@Test
	public void courseJava() throws IOException {
		Document doc01 =Jsoup.connect("https://bbs.bccn.net/forum-218-5.html")
                .userAgent("I’mjsoup") //设置User-Agent
                .cookie("auth", "token") //设置cookie
                .timeout(3000) //设置连接超时时间
                .post(); //使用POST方法访问URL)
		Elements eles01 = doc01.select(".title a[title]");
		for(Element ele01:eles01) {
			String postTitle = ele01.text();
			if(postTitle.contains("名录") || postTitle.contains("版主"))
				continue;
			Post post = new Post();
			Document doc02 =Jsoup.connect(ele01.absUrl("href")).get();
			Elements eles02 = doc02.select(".content");
			Integer postId = null;
			for(Element ele02:eles02) {
				if(ele02==eles02.first()) {
					/*----------对帖子操作开始-----------*/
					ele02.select(".mustlogin4see").remove();
					String postContent = ele02.html();
					post.setPostContent(postContent);
					post.setPostTitle(postTitle);
					PersonInfo user = new PersonInfo();
					user.setUserId(randomUserUseId());
					postId = service.deployPost(post,6, user);
					/*----------对帖子操作结束-----------*/
				}else {
					/*----------对楼信息操作开始-----------*/
					ele02.select(".mustlogin4see").remove();
					String floorContent = ele02.html();
					PersonInfo user = new PersonInfo();
					user.setUserId(randomUserUseId());
					Post post2 = new Post();
					post2.setPostId(postId);
					Floor floor = new Floor();
					floor.setFloorContent(floorContent);
					floorService.addFloor(floor, user, post);
					/*----------对楼信息操作结束-----------*/
				}
			}	
		}
	}

	public Integer randomUserUseId() {
		Random random = new Random();
		int size = list.size();
		Integer id = list.get(random.nextInt(size));
		System.out.println(id);
		return id;
	}
}



