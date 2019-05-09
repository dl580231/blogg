package com.nuc.a4q.entity;

import java.util.Date;

/**
 * 
 * @author lenovo
 *
 *         CREATE TABLE `tb_login_account` ( `account_id` int(11) NOT NULL,
 *         `owner_id` int(11) NOT NULL, `account_name` varchar(20) NOT NULL,
 *         `account_password` varchar(20) NOT NULL, `create_time` datetime
 *         DEFAULT NULL, `last_edit_time` datetime DEFAULT NULL, PRIMARY KEY
 *         (`account_id`), UNIQUE KEY `account_name` (`account_name`), KEY
 *         `owner_id` (`owner_id`), CONSTRAINT `tb_login_account_ibfk_1` FOREIGN
 *         KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`) )
 *         ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 
 * 
 */
public class LoginAccount {
	private Integer accountId;
	private Integer ownerId;
	private String accountName;
	private String accountPassword;
	private Date createTime;
	private Date lastEditTime;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
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

}
