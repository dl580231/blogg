package com.nuc.a4q.spider;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.dao.PostDao;
import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;

public class PostSpider {
	@Autowired
	PostDao dao;
	
	@Test
	public void courseJava() throws IOException {
		int a = 1;
		Document doc01 =Jsoup.connect("http://bbs.bccn.net/forum-8-1.html").get();
		Elements eles01 = doc01.select(".title a");
		for(Element ele01:eles01) {
			Post post = new Post();
			if(a==1) {
				a++;
				continue;
			}
			Document doc02 =Jsoup.connect(ele01.absUrl("href")).get();
			Elements eles02 = doc02.select(".content");
			for(Element ele02:eles02) {
				String postContent = ele02.html();
				String postTitle = ele01.text();
				post.setPostContent(postContent);
				post.setPostTitle(postTitle);
				post.setCreateTime(new Date());
				post.setLastEditTime(new Date());
				
				Course course = new Course();
				course.setCourseId(4);
				post.setCourse(course);
				
				PersonInfo user = new PersonInfo();
				user.setUserId(3);
				post.setDeployUser(user);
				dao.insertPost(post);
			}
		}
	}
}



