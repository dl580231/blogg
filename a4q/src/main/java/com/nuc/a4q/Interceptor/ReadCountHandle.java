package com.nuc.a4q.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nuc.a4q.service.PostService;

public class ReadCountHandle implements HandlerInterceptor {
	@Autowired
	PostService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String postIdRequest = request.getParameter("postId");
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
