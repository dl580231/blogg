package com.nuc.a4q.enums;

public enum ResultEnum {
	SUCCESS(0, "success"), INNER_ERROR(500, "未知错误"), FORM_AUTH_ERROR(-100, "表单验证出错"), DUPLICATE_EMAIL(50,
			"邮箱地址已被注册"), DUPLICATE_PHONE(51, "手机号已经被已被注册");
	private Integer state;
	private String stateInfo;

	private ResultEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public Integer getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

}
