package com.nuc.a4q.utils;

import javax.servlet.http.HttpServletRequest;

public class CodeUtils {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeImg = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCode = HttpServletRequestUtils.getString(request, "verifyCode");
		if(verifyCode.length()!=0&&verifyCode.equalsIgnoreCase(verifyCodeImg)) {
			return true;
		}
		return false;
	}
}
