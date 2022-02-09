package com.myprojects.admin.util.cardnumber;

import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.entity.CardNumbers;

public class CardNumberDTO {

	private Long id;
	private String no;
	private String name;
	private Long paymentTypeId;
	private String paymentName;
	private String image;
	private String createdTime;

	public CardNumberDTO() {
	}

	public CardNumberDTO(CardNumbers cn) {
		super();
		this.id = cn.getId();
		this.no = cn.getNo();
		this.name = cn.getName();
		this.paymentTypeId = cn.getPaymentType() != null ? cn.getPaymentType().getId() : null;
		this.paymentName = cn.getPaymentType() != null ? cn.getPaymentType().getName() : "";
		this.image = cn.getPaymentType() != null ? cn.getPaymentType().getImage() : "";
		this.createdTime = CommonUtil.changeDateToString(CommonConstant.STD_DATE_TIME_FORMAT, cn.getCreatedTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}
