package com.myprojects.admin.common.enumeration;

import java.util.ArrayList;
import java.util.List;

import com.myprojects.admin.common.CommonObject;

public enum OrderStatus {
	PENDING(1, "Pending"), CONFIRM(2, "Confirm"), CANCEL(3, "Cancel");

	private Integer code;
	private String desc;

	OrderStatus(int code, String desc) {
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
		for (OrderStatus cs : values()) {
			if (cs.getCode() == code) {
				return cs.getDesc();
			}
		}
		return "";
	}

	public static List<CommonObject> getAll() {
		List<CommonObject> typeList = new ArrayList<CommonObject>();
		for (OrderStatus t : OrderStatus.values()) {
			typeList.add(new CommonObject(t.getCode(), t.getDesc()));
		}
		return typeList;
	}
}
