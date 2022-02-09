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
@Table(name = TableName.SUPPLIER_CATEGORY)
public class SupplierCategory extends CommonEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "Supplier_Id")
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name = "Category_Id")
	private Category category;

	@Column(name = "CreatedTime")
	private Date createdTime;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
