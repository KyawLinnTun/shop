package com.myprojects.admin.common.enumeration;

import java.util.ArrayList;
import java.util.List;

import com.myprojects.admin.common.CommonObject;

public enum CommonStatus {
	ACTIVE(1, "Active"), INACTIVE(2, "Inactive");

	private Integer code;
	private String desc;

	CommonStatus(int code, String desc) {
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
		for (CommonStatus cs : values()) {
			if (cs.getCode() == code) {
				return cs.getDesc();
			}
		}
		return "";
	}

	public static List<CommonObject> getAll() {
		List<CommonObject> typeList = new ArrayList<CommonObject>();
		for (CommonStatus t : CommonStatus.values()) {
			typeList.add(new CommonObject(t.getCode(), t.getDesc()));
		}
		return typeList;
	}
}
