package com.nuc.a4q.Interceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminAuthInterceptor implements HandlerInterceptor{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了拦截器来劲了");
		Object loginAuth = request.getSession().getAttribute("user");
		if(loginAuth == null) {
			ServletOutputStream out = response.getOutputStream();
            out.print("unLogin");//返回给前端页面的未登陆标识
			out.flush();
			out.close();
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
