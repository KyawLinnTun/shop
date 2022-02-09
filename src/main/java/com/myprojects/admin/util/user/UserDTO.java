package com.myprojects.admin.util.user;

import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.CommonStatus;
import com.myprojects.admin.common.enumeration.UserType;
import com.myprojects.admin.entity.User;

public class UserDTO {
	private Long id;
	private String name;
	private String userName;
	private String password;
	private Integer userType;
	private String userTypeDesc;
	private Integer status;
	private String statusDesc;

	public UserDTO() {
	}

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.userType = user.getUserType();
		this.userTypeDesc = CommonUtil.validInteger(user.getUserType()) ? UserType.getDescByCode(user.getUserType())
				: "";
		this.status = user.getStatus();
		this.statusDesc = CommonUtil.validInteger(user.getStatus()) ? CommonStatus.getDescByCode(user.getStatus()) : "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUserTypeDesc() {
		return userTypeDesc;
	}

	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
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
