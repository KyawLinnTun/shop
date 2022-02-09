package com.myprojects.admin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myprojects.admin.common.CommonEntity;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.TableName;
import com.myprojects.admin.common.enumeration.OrderStatus;
import com.myprojects.admin.util.order.OrderDTO;

@Entity
@Table(name = TableName.ORDER_ITEM)
public class OrderItem extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "Item_Id")
	private Item item;

	@Column(name = "Count")
	private Integer count;

	@Column(name = "SinglePrice")
	private BigDecimal singlePrice;

	@Column(name = "TotalPrice")
	private BigDecimal totalPrice;

	@OneToOne
	@JoinColumn(name = "Order_Id")
	private Order order;

	@Column(name = "Status")
	private Integer status;

	@OneToOne
	@JoinColumn(name = "User_Id")
	private User user;

	@Column(name = "Description")
	private String description;

	@Column(name = "OrderDate")
	private Date orderDate;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

	public OrderItem() {
	}

	public OrderItem(OrderDTO dto) {
		super();
		this.count = dto.getCount();
		this.singlePrice = dto.getSinglePrice();
		this.totalPrice = dto.getTotalPrice();
		this.status = dto.getStatus();
		this.user = dto.getUserDTO() != null && CommonUtil.validLong(dto.getUserDTO().getId())
				? new User(dto.getUserDTO().getId())
				: null;
		this.orderDate = new Date();
		this.status = OrderStatus.PENDING.getCode();
		this.description=dto.getDescription();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(BigDecimal singlePrice) {
		this.singlePrice = singlePrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
