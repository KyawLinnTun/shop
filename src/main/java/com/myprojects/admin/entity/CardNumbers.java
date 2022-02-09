package com.myprojects.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.myprojects.admin.common.CommonEntity;
import com.myprojects.admin.common.TableName;

@Entity
@Table(name = TableName.CARD_NUMBERS)
public class CardNumbers extends CommonEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "No")
	private String no;

	@Column(name = "Name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "Payment_Type_Id")
	private PaymentType paymentType;

	@ManyToOne
	@JoinColumn(name = "Supplier_Id")
	private Supplier supplier;

	@Column(name = "CreatedTime")
	private Date createdTime;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
