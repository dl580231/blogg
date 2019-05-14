package com.nuc.a4q.web.PersonInfo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.a4q.entity.PageDivide;
import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.entity.Result;
import com.nuc.a4q.enums.ResultEnum;
import com.nuc.a4q.exception.LogicException;
import com.nuc.a4q.group.Delete;
import com.nuc.a4q.group.Insert;
import com.nuc.a4q.group.LoginAuth;
import com.nuc.a4q.group.Update;
import com.nuc.a4q.service.PersonInfoService;
import com.nuc.a4q.utils.CodeUtils;
import com.nuc.a4q.utils.HttpServletRequestUtils;
import com.nuc.a4q.utils.ResultUtil;

@Controller
@RequestMapping("personInfoAdmin")
public class PersonInfoManagementController {
	@Autowired
	private PersonInfoService service;

	/**
	 * 用户信息注册
	 * 
	 * @param personInfo
	 * @param bindingResult
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "userRegister", method = RequestMethod.POST)
	public Result userRegister(@Validated(Insert.class) PersonInfo personInfo, BindingResult bindingResult,
			HttpServletRequest request) {
		// 0.表单验证
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(ResultEnum.FORM_AUTH_ERROR.getState(),
					bindingResult.getFieldError().getDefaultMessage());
		}

		boolean verifyCodeResult = CodeUtils.checkVerifyCode(request);
		if (!verifyCodeResult) {
			throw new LogicException("验证码错误，请重新输入");
		}
		// 1.接受参数
		// 2.调用service层
		service.addPersoninfo(personInfo);
		// 3.返回结果
		return ResultUtil.success();
	}

	/**
	 * 用户登录认证
	 * 
	 * @param personInfo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "loginAuth", method = RequestMethod.POST)
	public Result loginAuth(@Validated(LoginAuth.class) PersonInfo personInfo, BindingResult result,
			HttpServletRequest request) {
		// 1.判断参数
		if (result.hasErrors()) {
			return ResultUtil.error(result.getFieldError().getDefaultMessage());
		}
		// 2.调用service层
		PersonInfo user = service.loginAuth(personInfo);
		request.getSession().setAttribute("user", user);
		// 3.返回结果
		return ResultUtil.success();
	}

	/**
	 * 获取用户分页信息
	 * 
	 * @param pageDivide
	 * @param personInfo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "getPersonInfoList", method = RequestMethod.POST)
	public Result getPersonInfoList(PageDivide pageDivide, PersonInfo personInfo) throws Exception {
		// 1.处理前端传来的数据
		// 2.调用service进行处理
		PageDivide pageDividResult = service.getPersonInfoList(pageDivide, personInfo);
		// 3.向前端返回数据
		return ResultUtil.success(pageDividResult);
	}

	/**
	 * 删除用户
	 * 
	 * @param personInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "removeUser", method = RequestMethod.GET)
	public Result removeUser(@Validated(value = Delete.class) PersonInfo personInfo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(ResultEnum.FORM_AUTH_ERROR.getState(),
					bindingResult.getFieldError().getDefaultMessage());
		}
		service.removeUser(personInfo);
		return ResultUtil.success();
	}

	/**
	 * 通过id获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUserById", method = RequestMethod.GET)
	public Result getUserById(Integer userId) {
		PersonInfo personInfo = service.getUserById(userId);
		return ResultUtil.success(personInfo);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param personInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public Result updateUser(@Validated(value = Update.class) PersonInfo personInfo, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(ResultEnum.FORM_AUTH_ERROR.getState(),
					bindingResult.getFieldError().getDefaultMessage());
		}
		service.updateUser(personInfo);
		return ResultUtil.success();
	}

	/**
	 * 登录状态验证
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "loginState", method = RequestMethod.GET)
	public Result loginState(HttpServletRequest request) {
		PersonInfo user = (PersonInfo) HttpServletRequestUtils.getSessionAttr(request, "user");
		if (user == null) {
			return ResultUtil.error("请登录");
		} else {
			return ResultUtil.success(user);
		}
	}
	
	/**
	 * 注销操作
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public Result logout(HttpServletRequest request) {
		HttpServletRequestUtils.rmSessionAttr(request, "user");
		return ResultUtil.success();
	}
}
