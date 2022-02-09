package com.myprojects.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myprojects.admin.common.CommonEntity;
import com.myprojects.admin.common.TableName;

@Entity
@Table(name = TableName.USER)
public class User extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "Name")
	private String name;

	@Column(name = "UserName")
	private String userName;

	@Column(name = "Password")
	private String password;

	@Column(name = "UserType")
	private Integer userType;

	@Column(name = "Status")
	private Integer status;

	public User() {
	}

	public User(Long id) {
		setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
