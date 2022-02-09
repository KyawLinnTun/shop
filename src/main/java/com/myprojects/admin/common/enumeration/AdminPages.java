package com.myprojects.admin.common.enumeration;

import java.util.ArrayList;
import java.util.List;

import com.myprojects.admin.common.CommonObject;

public enum AdminPages {

	DASHBOARD(1, "Dashboard"), USER(2, "User"), SUPPLIER(3, "Supplier"), NOTE(4, "Note"), ORDER(5, "Order"),
	ITEM(6, "Item");

	private Integer code;
	private String desc;

	AdminPages(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDescByCode(Integer code) {
		for (AdminPages dt : AdminPages.values()) {
			if (dt.getCode() == code) {
				return dt.getDesc();
			}
		}
		return "";
	}

	public static List<CommonObject> getAll() {
		List<CommonObject> typeList = new ArrayList<CommonObject>();
		for (AdminPages t : AdminPages.values()) {
			typeList.add(new CommonObject(t.getCode(), t.getDesc()));
		}
		return typeList;
	}
}
