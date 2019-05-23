package com.nuc.a4q.web.floor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.a4q.entity.Floor;
import com.nuc.a4q.entity.FloorNotice;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Post;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.group.Delete;
import com.nuc.a4q.group.Insert;
import com.nuc.a4q.service.FloorService;
import com.nuc.a4q.utils.HttpServletRequestUtils;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("floor")
public class FloorManagementController {
	@Autowired
	private FloorService service;

	/**
	 * 获得楼信息列表
	 * 
	 * @param floor
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getFloorList")
	public Result getFloorList(Floor floor, PersonInfo user) {
		List<Floor> list = service.getFloorList(floor, user);
		return ResultUtil.success(list);
	}

	/**
	 * 获取回帖的楼信息并且获得最佳恢复属于的楼层
	 * 
	 * @param floor
	 * @param user
	 * @param isResolved
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getFloorListWithNum")
	public Result getFloorListWithNum(Floor floor, PersonInfo user, Integer isResolved) {
		return service.getFloorListWithNum(floor, user, isResolved);
	}

	/**
	 * 删除楼回复
	 * 
	 * @param floor
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "removeFloor", method = RequestMethod.GET)
	public Result removeFloor(@Validated(Delete.class) Floor floor, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			return ResultUtil.error(result.getFieldError().getDefaultMessage());
		}
		service.removeFloor(floor);
		Post post = (Post) HttpServletRequestUtils.getSessionAttr(request, "currentPost");
		return ResultUtil.success(post.getPostId());
	}

	@ResponseBody
	@RequestMapping(value = "addFloor", method = RequestMethod.POST)
	public Result addFloor(@Validated(Insert.class) Floor floor, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			return ResultUtil.error(result.getFieldError().getDefaultMessage());
		}
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");

		Post post = (Post) HttpServletRequestUtils.getSessionAttr(request, "currentPost");

		service.addFloor(floor, user, post);
		return ResultUtil.success(post.getPostId());
	}
	
	@ResponseBody
	@RequestMapping(value = "getNoticeFloor", method = RequestMethod.GET)
	public Result getNoticeFloor(HttpServletRequest request) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request,"user");
		List<FloorNotice> list = service.getNoticeFloor(user);
		return ResultUtil.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "lookOver", method = RequestMethod.GET)
	public Result lookOver(HttpServletRequest request,Integer floorId) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request,"user");
		service.lookOver(floorId, user);
		return ResultUtil.success();
	}
	
	@ResponseBody
	@RequestMapping(value = "lookOverDelete", method = RequestMethod.GET)
	public Result lookOverDelete(HttpServletRequest request,Integer floorId) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		service.lookOverDelete(floorId, user);
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
