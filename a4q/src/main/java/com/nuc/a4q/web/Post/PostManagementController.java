package com.nuc.a4q.web.Post;

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

import com.nuc.a4q.entity.Course;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.entity.UserRank;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.group.Delete;
import com.nuc.a4q.group.Insert;
import com.nuc.a4q.group.Update;
import com.nuc.a4q.service.PostService;
import com.nuc.a4q.utils.HttpServletRequestUtils;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("/post")
public class PostManagementController {
	@Autowired
	private PostService service;

	/**
	 * 查询帖子信息
	 * 
	 * @param post
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPostList")
	public Result getPostList(Post post, Integer courseId, Integer userId) {
		if (courseId != null) {
			Course course = new Course();
			course.setCourseId(courseId);
			post.setCourse(course);
		}
		if (userId != null) {
			PersonInfo personInfo = new PersonInfo();
			personInfo.setUserId(userId);
			post.setDeployUser(personInfo);
		}
		List<Post> list = service.queryPostList(post);
		return ResultUtil.success(list);
	}

	/**
	 * 删除post信息
	 * 
	 * @param post
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removePost", method = RequestMethod.GET)
	public Result removePost(@Validated(value = Delete.class) Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new LogicException(bindingResult.getFieldError().getDefaultMessage());
		}
		service.removePost(post);
		return ResultUtil.success();
	}

	/**
	 * 帖子置顶操作，查出最小priority并减一
	 * 
	 * @param postId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/topPost", method = RequestMethod.GET)
	public Result topPost(@Validated(value = Update.class) Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new LogicException(bindingResult.getFieldError().getDefaultMessage());
		}
		service.topPost(post);
		return ResultUtil.success();
	}

	/**
	 * 帖子置顶操作，查出最小priority并减一
	 * 
	 * @param postId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bottomPost", method = RequestMethod.GET)
	public Result bottomPost(@Validated(value = Update.class) Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new LogicException(bindingResult.getFieldError().getDefaultMessage());
		}
		service.bottomPost(post);
		return ResultUtil.success();
	}

	/**
	 * 通过优先级获得已解决问题的列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getResolvedPost", method = RequestMethod.GET)
	public Result getResolvedPost(Integer rowStart, Integer rowSize, Integer courseId) {
		HashMap<Object, Object> map = service.getResolvedPost(rowStart, rowSize, courseId);
		return ResultUtil.success(map);
	}

	/**
	 * 通过优先级获得未问题的列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUnResolvedPost", method = RequestMethod.GET)
	public Result getUnResolvedPost(Integer rowStart, Integer rowSize, Integer courseId) {
		HashMap<Object, Object> map = service.getUnResolvedPost(rowStart, rowSize, courseId);
		return ResultUtil.success(map);
	}

	@ResponseBody
	@RequestMapping(value = "getUserRank", method = RequestMethod.GET)
	public Result getUserRank() {
		List<UserRank> userList = service.getUserRank();
		return ResultUtil.success(userList);
	}

	/**
	 * 通过优先级获得已解决问题的列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getResolved", method = RequestMethod.GET)
	public Result getResolved(Integer courseId, String key) {
		String postContent = "";
		String postTitle = "";
		if (key != null && key.length() > 0) {
			postContent = key;
			postTitle = key;
		}
		List<Post> list = service.getResolved(courseId, postContent, postTitle);
		return ResultUtil.success(list);
	}

	/**
	 * 通过优先级获得未问题的列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUnResolved", method = RequestMethod.GET)
	public Result getUnResolved(Integer courseId, String key) {
		String postContent = "";
		String postTitle = "";
		if (key != null && key.length() > 0) {
			postContent = key;
			postTitle = key;
		}
		List<Post> list = service.getUnResolved(courseId, postContent, postTitle);
		return ResultUtil.success(list);
	}

	/**
	 * 发表帖子
	 * 
	 * @param post
	 * @param result
	 * @param courseId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deployPost", method = RequestMethod.POST)
	public Result deployPost(@Validated(Insert.class) Post post, BindingResult result, Integer courseId,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return ResultUtil.error(result.getFieldError().getDefaultMessage());
		}
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		if (user == null) {
			return ResultUtil.error("发表问题之前请登录,登录成功后刷新页面");
		}
		Integer postId = service.deployPost(post, courseId, user);
		return ResultUtil.success(postId);
	}

	@ResponseBody
	@RequestMapping(value = "getPostById", method = RequestMethod.GET)
	public Result getPostById(Integer postId, HttpServletRequest request) {
		Post post = service.getPostById(postId);
		request.getSession().setAttribute("currentPost", post);
		return ResultUtil.success(post);
	}

	/**
	 * 指定帖子最佳答案
	 * 
	 * @param floorId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "electBestAnswer", method = RequestMethod.GET)
	public Result electBestAnswer(Integer floorId, HttpServletRequest request) {
		Post post = (Post) HttpServletRequestUtils.getSessionAttr(request, "currentPost");
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		service.electBestAnswer(floorId, user, post);
		return ResultUtil.success(post.getPostId());
	}
	
	/**
	 * 根据postId获得对应阅读量
	 * @param floorId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getReadCountById", method = RequestMethod.GET)
	public Result getReadCountById(Integer postId) {
		Integer readCount = service.getReadCountById(postId);
		return ResultUtil.success(readCount);
	}
	
	@ResponseBody
	@RequestMapping(value = "getPostRankByReadCount", method = RequestMethod.GET)
	public Result getPostRankByReadCount() {
		List<Post> list = service.getPostRankByReadCount();
		return ResultUtil.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "getAnswerPost", method = RequestMethod.GET)
	public Result getAnswerPost(Integer userId) {
		List<Post> list = service.getAnswerPost(userId);
		return ResultUtil.success(list);
	}
	
	/**
	 * 逻辑删除
	 * @param postId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logicRmPost", method = RequestMethod.GET)
	public Result logicRmPost(HttpServletRequest request,Integer postId) {
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		service.logicRmpost(postId,user);
		return ResultUtil.success();
	}
	
	@ResponseBody
	@RequestMapping(value = "getRecommendPost", method = RequestMethod.GET)
	public Result getRecommendPost(HttpServletRequest request,Integer userId) throws TasteException {
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		List<Post> list = service.getRecommendPost(user.getUserId());
		HashMap<Object, Object> map = new HashMap<Object,Object>();
		map.put("list", list);
		return ResultUtil.success(map);
	}
}
