package com.nuc.a4q.entity;

public class UserRank {
	private Integer num;
	private Integer userId;
	private String userName;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserRank [num=" + num + ", userId=" + userId + ", userName=" + userName + "]";
	}

}
