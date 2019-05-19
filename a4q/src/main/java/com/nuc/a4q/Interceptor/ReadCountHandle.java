package com.nuc.a4q.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.service.PostHistoryService;
import com.nuc.a4q.service.PostService;

public class ReadCountHandle implements HandlerInterceptor {
	@Autowired
	PostService service;
	@Autowired
	PostHistoryService historyService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String postIdRequest = request.getParameter("postId");
		/*--------操作阅读数量开始---------*/
		if(postIdRequest!="") {
			String postId = (String) session.getAttribute("postId");
			if(postId==null)
				postId = "";
			if(!postId.equals(postIdRequest)) {
				session.setAttribute("postId", postIdRequest);
				service.readCountAdd(Integer.parseInt(postIdRequest));
			}
		}else {
			return false;
		}
		/*--------操作阅读数量结束---------*/
		
		/*--------操作浏览记录开始---------*/
		PersonInfo user = (PersonInfo) session.getAttribute("user");
		if(user != null) {
			historyService.addPostHistory(user.getUserId(), Integer.parseInt(postIdRequest));
		}
		/*--------操作浏览记录结束---------*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
