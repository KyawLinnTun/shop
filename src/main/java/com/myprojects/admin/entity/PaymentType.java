package com.myprojects.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myprojects.admin.common.CommonEntity;
import com.myprojects.admin.common.TableName;

@Entity
@Table(name = TableName.PAYMENT_TYPE)
public class PaymentType extends CommonEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "Name")
	private String name;

	@Column(name = "Image")
	private String image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
