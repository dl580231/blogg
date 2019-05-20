package com.nuc.a4q.service;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.a4q.BaseTest;
import com.nuc.a4q.entity.Post;

public class PostServiceTest extends BaseTest {

	@Autowired
	private PostService service;
	
	@Test
	@Ignore
	public void getRecommendPostTest() throws TasteException {
		List<Post> list = service.getRecommendPost(27);
		System.out.println(list);
	}
}
