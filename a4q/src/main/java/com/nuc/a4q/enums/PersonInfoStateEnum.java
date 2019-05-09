package com.nuc.a4q.enums;

public enum PersonInfoStateEnum {
	SUCCESS(1, "处理用户信息成功"), AUTH_FILED(-999, "用户名或密码错误"), NOT_EXIST(-1000, "用户信息不存在"), NULL_INFO(-1001,
			"用户信息为空"), INNER_ERROR(1002, "内部错误");

	private Integer state;
	private String stateInfo;

	private PersonInfoStateEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public PersonInfoStateEnum stateOf(Integer state) {
		for (PersonInfoStateEnum personInfoStateEnum : values()) {
			if (personInfoStateEnum.state == state) {
				return personInfoStateEnum;
			}
		}
		return null;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

}
