package com.nuc.a4q.web.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.group.Delete;
import com.nuc.a4q.group.Insert;
import com.nuc.a4q.group.Update;
import com.nuc.a4q.service.CourseService;
import com.nuc.a4q.service.PersonInfoService;
import com.nuc.a4q.service.PostService;
import com.nuc.a4q.utils.HttpServletRequestUtils;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("course")
public class CourseManagementController {
	@Autowired
	private CourseService service;
	@Autowired
	private PersonInfoService userService;
	@Autowired
	private PostService postService;

	/**
	 * 获取所有的课程名称
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCourseListName", method = RequestMethod.GET)
	public Result getCourseListName() {
		List<Course> list = service.getCourseListName();
		return ResultUtil.success(list);
	}

	/**
	 * 根据条件获得课程列表信息
	 * 
	 * @param course
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getCourseList")
	public Result getCourseList(Course course, Integer userId) {
		if (userId != null) {
			PersonInfo user = new PersonInfo();
			user.setUserId(userId);
			course.setModerator(user);
		}
		List<Course> list = service.getCourseList(course);
		return ResultUtil.success(list);
	}

	/**
	 * 删除课程信息
	 * 
	 * @param course
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "removeCourse", method = RequestMethod.GET)
	public Result removeCourse(@Validated(value = Delete.class) Course course,BindingResult result) {
		if(result.hasErrors()) {
			return ResultUtil.error(result.getFieldError().getDefaultMessage());
		}
		service.removeCurse(course);
		return ResultUtil.success();
	}

	/**
	 * 更新课程信息
	 * 
	 * @param moderatorId
	 * @param course
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateCourse", method = RequestMethod.POST)
	public Result updateCourse(Integer moderatorId, @Validated(value = Update.class) Course course,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new LogicException(result.getFieldError().getDefaultMessage());
		}
		if (moderatorId != null) {
			PersonInfo judgeExist = userService.getUserById(moderatorId);
			if (judgeExist != null) {
				PersonInfo user = new PersonInfo();
				user.setUserId(moderatorId);
				course.setModerator(user);
			}
		}
		service.updateCourse(course);
		return ResultUtil.success();
	}

	/**
	 * 增加课程信息
	 * 
	 * @param course
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addCourse", method = RequestMethod.POST)
	public Result addCourse(@Validated(Insert.class) Course course, BindingResult result) {
		if (result.hasErrors()) {
			throw new LogicException(result.getFieldError().getDefaultMessage());
		}
		service.addCourse(course);
		return ResultUtil.success();
	}

	@ResponseBody
	@RequestMapping(value = "getPostCountByCourseId", method = RequestMethod.GET)
	public Result getPostCountByCourseId(Integer courseId) {
		Integer count = postService.getPostCountByCourseId(courseId);
		return ResultUtil.success(count);
	}

	/**
	 * 版主权限认证
	 * @param request
	 * @param courseId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "moderatorJudge", method = RequestMethod.GET)
	public Result moderatorJudge(HttpServletRequest request, Integer courseId) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		if (user == null) {
			return ResultUtil.error("用户状态未登陆");
		}
		Post post = (Post) HttpServletRequestUtils.getSessionAttr(request, "currentPost");
		if(post == null) {
			return ResultUtil.error("未查询到post信息");
		}
		Boolean result = service.moderatorJudge(user, post.getCourse().getCourseId());
		if (result) {
			return ResultUtil.success();
		} else {
			return ResultUtil.error("不是版主");
		}
	}
}