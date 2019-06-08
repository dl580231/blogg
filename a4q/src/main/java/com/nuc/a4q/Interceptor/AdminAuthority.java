package com.nuc.a4q.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nuc.a4q.utils.HttpServletRequestUtils;

public class AdminAuthority implements HandlerInterceptor{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getHeader("Referer");
		if(url==null) {}
		else if(url.contains("back")) {
			if(url.contains("login")) {
				
			}
			else {
				HttpServletRequestUtils.getSessionAttr(request, "admin");
			    String admin = (String) request.getSession().getAttribute("admin");
			    if(admin.length()!=0)
			    	return true;
			    else
			    	return false;
			}
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
