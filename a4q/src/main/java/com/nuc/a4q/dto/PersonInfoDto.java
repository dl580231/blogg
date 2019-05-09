package com.nuc.a4q.dto;

import java.util.List;

import com.nuc.a4q.entity.PersonInfo;
import com.nuc.a4q.enums.PersonInfoStateEnum;

public class PersonInfoDto {
	private PersonInfoStateEnum enum1;
	private PersonInfo personInfo;
	private List<PersonInfo> personInfoList;
	public Integer count;

	/**
	 * 默认的构造方法
	 */
	public PersonInfoDto() {
	}

	public PersonInfoDto(PersonInfoStateEnum enum1) {
		this.enum1 = enum1;
	}

	public PersonInfoDto(PersonInfoStateEnum enum1, PersonInfo personInfo) {
		this.enum1 = enum1;
		this.personInfo = personInfo;
	}

	public PersonInfoDto(PersonInfoStateEnum enum1, List<PersonInfo> personInfoList, Integer count) {
		this.enum1 = enum1;
		this.personInfoList = personInfoList;
		this.count = count;
	}

	public PersonInfoStateEnum getEnum1() {
		return enum1;
	}

	public void setEnum1(PersonInfoStateEnum enum1) {
		this.enum1 = enum1;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
