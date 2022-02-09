package com.myprojects.admin.common;

import java.io.Serializable;

public class CommonAjaxResponse implements Serializable{

	private static final long serialVersionUID = -1653575223351014032L;
	
	private String information;
	private String emptyData;
	private String data;
	private String successMessage;	
	
	public CommonAjaxResponse() {
		//do nothing
	}

	public CommonAjaxResponse(String information, String data) {
		this.information=information;
		this.data=data;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEmptyData() {
		return emptyData;
	}

	public void setEmptyData(String emptyData) {
		this.emptyData = emptyData;
	}

	
	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

}
