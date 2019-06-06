package com.nuc.a4q.web.Blog;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.a4q.dto.BlogDto;
import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.entity.UserRank;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.group.Insert;
import com.nuc.a4q.group.Update;
import com.nuc.a4q.service.BlogService;
import com.nuc.a4q.utils.HttpServletRequestUtils;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("blog")
public class BlogManagement {
	@Autowired
	private BlogService service;

	@ResponseBody
	@RequestMapping(value="addBlog",method=RequestMethod.POST)
	public Result addBlog(HttpServletRequest request,@Validated(value=Insert.class)Blog blog,BindingResult result) {
		if (result.hasErrors()) {
			throw new LogicException(result.getFieldError().getDefaultMessage());
		}
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		Integer blogId = service.addBlog(blog,user);
		return ResultUtil.success(blogId);
	}

	@ResponseBody
	@RequestMapping(value="getBlogById",method=RequestMethod.GET)
	public Result getBlogById(Integer blogId,HttpServletRequest request) {
		BlogDto data = service.getBlogById(blogId);
		request.getSession().setAttribute("blog",data.getBlog());
		return ResultUtil.success(data);
	}
	
	@ResponseBody
	@RequestMapping(value="getBlogList",method=RequestMethod.GET)
	public Result getBlogList(Blog blog) {
		List<Blog> list = service.getBlogList(blog);
		return ResultUtil.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="logicRm",method=RequestMethod.GET)
	public Result logicRm(HttpServletRequest request,Integer blogId) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		service.logicRm(blogId,user);
		return ResultUtil.success();
	}
	
	@ResponseBody
	@RequestMapping(value = "getUserRank", method = RequestMethod.GET)
	public Result getUserRank() {
		List<UserRank> userList = service.getUserRank();
		return ResultUtil.success(userList);
	}
	
	@ResponseBody
	@RequestMapping(value = "getBlogRankByReadCount", method = RequestMethod.GET)
	public Result getPostRankByReadCount() {
		List<Blog> list = service.getBlogRankByReadCount();
		return ResultUtil.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "getBlogLoad", method = RequestMethod.GET)
	public Result getResolvedPost(Integer rowStart, Integer rowSize, Integer courseId) {
		HashMap<Object, Object> map = service.getBlogOrderByPriority(rowStart, rowSize, courseId);
		return ResultUtil.success(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "getRecommendBlog", method = RequestMethod.GET)
	public Result getRecommendBlog(HttpServletRequest request) throws TasteException {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		List<BlogDto> list = service.getRecommendBlog(user.getUserId());
		HashMap<Object, Object> map = new HashMap<Object,Object>();
		map.put("list", list);
		return ResultUtil.success(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "getSerachBlog", method = RequestMethod.GET)
	public Result getSerachBlog(String key) {
		String blogContent = "";
		String blogTitle = "";
		if (key != null && key.length() > 0) {
			blogContent = key;
			blogTitle = key;
		}
		List<BlogDto> list = service.getSerachBlog(blogContent, blogTitle);
		return ResultUtil.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="getBlogByIdE",method=RequestMethod.GET)
	public Result getBlogByIdE(Integer blogId,HttpServletRequest request) {
		BlogDto data = service.getBlogById(blogId);
		return ResultUtil.success(data);
	}
	
	@ResponseBody
	@RequestMapping(value="editBlog",method=RequestMethod.POST)
	public Result editBlog(@Validated(value=Update.class)Blog blog,HttpServletRequest request,BindingResult result) {
		if (result.hasErrors()) {
			throw new LogicException(result.getFieldError().getDefaultMessage());
		}
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		service.editBlog(blog,user);
		return ResultUtil.success();
	}
	
	@ResponseBody
	@RequestMapping(value="editJudge",method=RequestMethod.GET)
	public Result editJudge(HttpServletRequest request,Blog blog) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		boolean result = service.judgeBelong(blog,user);
		return ResultUtil.success(result);
	}
	
}
