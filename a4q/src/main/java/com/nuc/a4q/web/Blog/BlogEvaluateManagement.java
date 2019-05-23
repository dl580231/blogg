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

import com.nuc.a4q.dto.BlogEvaluateDto;
import com.nuc.a4q.entity.Blog;
import com.nuc.a4q.entity.BlogEvaluate;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.group.Insert;
import com.nuc.a4q.service.BlogEvaluateService;
import com.nuc.a4q.utils.HttpServletRequestUtils;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("blogEvaluate")
public class BlogEvaluateManagement {
	@Autowired
	private BlogEvaluateService service;

	@ResponseBody
	@RequestMapping(value="addEvaluate",method=RequestMethod.POST)
	public Result addEvaluate(HttpServletRequest request,@Validated(value=Insert.class)BlogEvaluate evaluate,BindingResult result) {
		if (result.hasErrors()) {
			throw new LogicException(result.getFieldError().getDefaultMessage());
		}
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		Blog blog = (Blog) HttpServletRequestUtils.getSessionAttr(request, "blog");
		service.addEvaluate(user,evaluate,blog);
		return ResultUtil.success();
	}
	
	@ResponseBody
	@RequestMapping(value="getEvaluateList",method=RequestMethod.GET)
	public Result getEvaluateList(HttpServletRequest request,BlogEvaluate evaluate) {
		Blog blog = (Blog) HttpServletRequestUtils.getSessionAttr(request, "blog");
		List<BlogEvaluateDto> list = service.getEvaluateList(blog,evaluate);
		return ResultUtil.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="getNoticeEvaluate",method=RequestMethod.GET)
	public Result getNoticeEvaluate(HttpServletRequest request) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request,"user");
		List<BlogEvaluateDto> list = service.getNoticeEvaluate(user);
		return ResultUtil.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "lookOver", method = RequestMethod.GET)
	public Result lookOver(HttpServletRequest request,Integer evaluateId) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request,"user");
		service.lookOver(evaluateId, user);
		return ResultUtil.success();
	}
	
	@ResponseBody
	@RequestMapping(value = "lookOverDelete", method = RequestMethod.GET)
	public Result lookOverDelete(HttpServletRequest request,Integer evaluateId) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		service.lookOverDelete(evaluateId, user);
		return ResultUtil.success();
	}
	
	@ResponseBody
	@RequestMapping(value = "lookOverDeleteAll", method = RequestMethod.GET)
	public Result lookOverDeleteAll(HttpServletRequest request) {
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		service.lookOverDeleteAll(user);
		return ResultUtil.success();
	}
}
