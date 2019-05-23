package com.nuc.a4q.utils;

import javax.servlet.http.HttpServletRequest;

import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.exception.LogicException;

public class HttpServletRequestUtils {
	/**
	 * 根据传来值返回存在session中的对应属性
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getSessionAttr(HttpServletRequest request, String key) {
		Object data = request.getSession().getAttribute(key);
		if(data == null) {
			throw new LogicException(key+"为空");
		}
		return data;
	}
	
	public static void rmSessionAttr(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.decode(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}

	public static long getLong(HttpServletRequest request, String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}

	public static Double getDouble(HttpServletRequest request, String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1d;
		}
	}

	public static boolean getBoolean(HttpServletRequest request, String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return false;
		}
	}

	public static String getString(HttpServletRequest request, String key) {
		try {
			String result = request.getParameter(key);
			if (result != null) {
				result = result.trim();
			}
			if ("".equals(result)) {
				result = null;
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 判断登录用户和操作用户是否为同一个人
	 * @param request
	 * @param userId
	 * @return
	 */
	public static Boolean judgeUser(HttpServletRequest request, Integer userId) {
		PersonInfo user = (PersonInfo) getSessionAttr(request, "user");
		if(user.getUserId().equals(userId)) {
			return true;
		}
		return false;
	}
}
