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
import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.BlogEvaluate;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.service.BlogEvaluateService;
import com.nuc.a4q.service.BlogService;


public class BlogSpider extends BaseTest {
	@Autowired
	BlogService blogService;
	@Autowired
	BlogEvaluateService evaluateService;
	
	ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,3,4,9,14,15,16,17,18,19,20,26,27,28));
	
	@Test
//	@Ignore
	public void blogJava() throws IOException {
		String url = "https://blog.bccn.net/%E9%9D%99%E5%A4%9C%E6%80%9D/categories/19256";
		Document doc01 =Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36") //设置User-Agent
                .cookie("auth", "token") //设置cookie
                .timeout(3000) //设置连接超时时间
                .get(); //使用POST方法访问URL
		Elements eles01 = doc01.select(".boke_list a");
		for(Element ele01:eles01) {
			String blogTitle = ele01.text();
			Blog blog = new Blog();
			Document doc02 =Jsoup.connect(ele01.absUrl("href")).get();
			Elements ele02 = doc02.select(".content");
			/*----------对blog操作开始-----------*/
			String blogContent = ele02.html().replaceAll("brush:", "");
			blog.setBlogContent(blogContent);
			blog.setBlogTitle(blogTitle);
			blog.setCourseId(6);
			PersonInfo user = new PersonInfo();
			user.setUserId(randomUserUseId());
			blogService.addBlog(blog,user);
			/*----------对blog操作结束-----------*/
			
			/*----------对楼信息操作开始-----------*/
			Elements evaluates = doc02.select(".comment_content");
			for(Element evaluate:evaluates) {
				String evaluateContent = evaluate.html();
				user = new PersonInfo();
				user.setUserId(randomUserUseId());
				BlogEvaluate eval = new BlogEvaluate();
				eval.setEvaluateContent(evaluateContent);
				evaluateService.addEvaluate(user,eval,blog);
			}
			/*----------对楼信息操作结束-----------*/
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



