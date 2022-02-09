package com.myprojects.admin.common;

import java.io.Serializable;

public class CommonObject implements Serializable {

	private static final long serialVersionUID = -7101626049771290040L;
	
	private Integer code;
	
	private String desc;

	public CommonObject() {
		super();
	}

	public CommonObject(Integer code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
