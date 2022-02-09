package com.myprojects.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myprojects.admin.common.CommonEntity;
import com.myprojects.admin.common.TableName;

@Entity
@Table(name = TableName.SUPPLIER)
public class Supplier extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name")
	private String name;
	@Column(name = "PhoneNo")
	private String phoneNo;
	@Column(name = "Address")
	private String address;
	@Column(name = "Status")
	private Integer status;
	
	public Supplier() {
	}

	public Supplier(Long id) {
		this.setId(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
