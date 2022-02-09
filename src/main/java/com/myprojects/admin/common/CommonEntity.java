package com.myprojects.admin.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Long id;

	public CommonEntity() {
		super();
	}

	public CommonEntity(Long id, Date createdTime, Date updatedTime) {
		super();
		this.id = id;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public Date getCreatedTime() { return createdTime; }
	 * 
	 * public void setCreatedTime(Date createdTime) { this.createdTime =
	 * createdTime; }
	 * 
	 * public Date getUpdatedTime() { return updatedTime; }
	 * 
	 * public void setUpdatedTime(Date updatedTime) { this.updatedTime =
	 * updatedTime; }
	 */

}
