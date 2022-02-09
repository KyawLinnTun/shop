package com.myprojects.admin.common.enumeration;

import java.util.ArrayList;
import java.util.List;

import com.myprojects.admin.common.CommonObject;

public enum UserType {
	EMPLOYEER(1, "Employeer"), EMPLOYEE(2, "Employee");

	private Integer code;
	private String desc;

	UserType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDescByCode(int code) {
		for (UserType cs : values()) {
			if (cs.getCode() == code) {
				return cs.getDesc();
			}
		}
		return "";
	}

	public static List<CommonObject> getAll() {
		List<CommonObject> typeList = new ArrayList<CommonObject>();
		for (UserType t : UserType.values()) {
			typeList.add(new CommonObject(t.getCode(), t.getDesc()));
		}
		return typeList;
	}

}
