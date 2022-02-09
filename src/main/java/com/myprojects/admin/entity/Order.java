package com.myprojects.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonEntity;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.TableName;
import com.myprojects.admin.util.order.OrderDTO;

@Entity
@Table(name = TableName.ORDER)
public class Order extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "Supplier_Id")
	private Supplier supplier;

	@Column(name = "ReceivedDate")
	private Date receivedDate;

	public Order() {
	}

	public Order(OrderDTO dto) {
		super();
		this.supplier = dto.getSupplierDTO() != null && CommonUtil.validLong(dto.getSupplierDTO().getId())
				? new Supplier(dto.getSupplierDTO().getId())
				: null;
		this.receivedDate = CommonUtil.validString(dto.getReceivedDate())
				? CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT, dto.getReceivedDate())
				: null;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

}
