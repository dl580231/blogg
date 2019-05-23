package com.nuc.a4q.web.Blog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.group.Insert;
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
}
