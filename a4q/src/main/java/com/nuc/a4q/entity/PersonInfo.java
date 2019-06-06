package com.nuc.a4q.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.nuc.a4q.group.Delete;
import com.nuc.a4q.group.Insert;
import com.nuc.a4q.group.LoginAuth;
import com.nuc.a4q.group.Update;

/**
 * 
 * @author DL
 *
 *         CREATE TABLE `tb_person_info` ( `user_id` int(11) NOT NULL
 *         AUTO_INCREMENT, `user_name` varchar(255) NOT NULL, `gender`
 *         varchar(2) DEFAULT NULL, `profile_img` varchar(1024) DEFAULT NULL,
 *         `phone` varchar(20) DEFAULT NULL, `email` varchar(20) DEFAULT NULL,
 *         `user_type` int(2) NOT NULL DEFAULT '0' COMMENT '0：学生；1.老师', `lable`
 *         varchar(255) DEFAULT NULL COMMENT '个人标签', `create_time` datetime
 *         DEFAULT NULL, `last_edit_time` datetime DEFAULT NULL, PRIMARY KEY
 *         (`user_id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 
 * 
 * 
 * 
 */

public class PersonInfo {
	@NotNull(message="用户ID不能为空")
	/*@Pattern(regexp="^(\\d)+$",message="用户ID格式匹配错误",groups= {Update.class})*/
	private Integer userId;
	@NotBlank(message = "用户名字不能为空",groups= {Insert.class,Update.class,Delete.class})
	private String userName;// 用户的名字
	private String gender;// 用户性别
	private String profileImg;// 用户头像地址
	@NotNull(message = "手机号不能为空",groups= {Insert.class})
	@Pattern(regexp = "^1[\\d]{10}$", message = "电话号码格式有误",groups= {Insert.class,Update.class,LoginAuth.class})
	private String phone;
	@NotBlank(message = "邮箱地址不能为空",groups= {Insert.class})
	@Email(message = "邮箱地址格式出错",groups= {Insert.class,Update.class,LoginAuth.class})
	private String email;
	@NotBlank(message = "密码不能为空",groups= {Insert.class,LoginAuth.class})
	@Size(min = 6, max = 15, message = "密码为6-15的大小写字母+数字组合",groups= {Insert.class,Update.class})
	@Pattern(regexp = "[a-z|A-z|0-9]{6,15}", message = "密码为6-15的大小写字母+数字组合",groups= {Insert.class})
	private String password;
	@NotBlank(message = "用户身份不能为空",groups= {Insert.class})
	@Pattern(regexp = "^(学生|老师)$", message = "用户身份输入错误",groups= {Insert.class,Update.class})
	private String userType;// 用户身份 :学生 ,老师
	private String lable;// 用户标签
	private Date createTime;
	private Date lastEditTime;
	/**
	 * 判断用户是否存在通知
	 */
	private Integer notice;

	public Integer getNotice() {
		return notice;
	}

	public void setNotice(Integer notice) {
		this.notice = notice;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PersonInfo [userId=" + userId + ", userName=" + userName + ", gender=" + gender + ", profileImg="
				+ profileImg + ", phone=" + phone + ", email=" + email + ", password=" + password + ", userType="
				+ userType + ", lable=" + lable + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
