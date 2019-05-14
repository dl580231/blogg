package com.nuc.a4q.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtils {
	/**
	 * 根据传来值返回存在session中的对应属性
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getSessionAttr(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
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
}
