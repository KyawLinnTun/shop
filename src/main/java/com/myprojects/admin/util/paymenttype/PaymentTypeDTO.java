package com.myprojects.admin.util.paymenttype;

import com.myprojects.admin.entity.PaymentType;

public class PaymentTypeDTO {

	private Long id;
	private String name;
	private String image;

	public PaymentTypeDTO() {
	}

	public PaymentTypeDTO(PaymentType paymentType) {
		super();
		this.id = paymentType.getId();
		this.name = paymentType.getName();
		this.image = paymentType.getImage();
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
