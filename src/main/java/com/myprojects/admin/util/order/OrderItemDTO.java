package com.myprojects.admin.util.order;

import java.math.BigDecimal;

import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.OrderStatus;
import com.myprojects.admin.entity.OrderItem;
import com.myprojects.admin.util.item.ItemDTO;
import com.myprojects.admin.util.user.UserDTO;

public class OrderItemDTO {

	private ItemDTO item;
	private Integer count;
	private BigDecimal singlePrice;
	private BigDecimal totalPrice;
	private Integer status;
	private String statusDesc;
	private UserDTO userDTO;
	private String orderDate;
	private String updatedDate;

	public OrderItemDTO() {
	}

	public OrderItemDTO(OrderItem orderItem) {
		super();
		this.item = orderItem.getItem() != null ? new ItemDTO(orderItem.getItem()) : null;
		this.count = orderItem.getCount();
		this.singlePrice = orderItem.getSinglePrice();
		this.totalPrice = orderItem.getTotalPrice();
		this.status = orderItem.getStatus();
		this.statusDesc = CommonUtil.validInteger(orderItem.getStatus())
				? OrderStatus.getDescByCode(orderItem.getStatus())
				: "";
		this.userDTO = orderItem.getUser() != null ? new UserDTO(orderItem.getUser()) : null;
		this.orderDate = orderItem.getOrderDate() != null
				? CommonUtil.changeDateToString(CommonConstant.STD_DATE_TIME_FORMAT, orderItem.getOrderDate())
				: null;
		this.updatedDate = orderItem.getUpdatedDate() != null
				? CommonUtil.changeDateToString(CommonConstant.STD_DATE_TIME_FORMAT, orderItem.getUpdatedDate())
				: null;
	}

	public ItemDTO getItem() {
		return item;
	}

	public void setItem(ItemDTO item) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
