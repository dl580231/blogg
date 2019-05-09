package com.nuc.a4q.Filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminAuthFilter implements HandlerInterceptor{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了");
		Object loginAuth = request.getSession().getAttribute("adminUser");
		if(loginAuth == null) {
			response.sendRedirect("/");
			return false;
		}else {
			return true;
		}
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
