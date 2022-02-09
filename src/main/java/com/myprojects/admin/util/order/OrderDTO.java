package com.myprojects.admin.util.order;

import java.math.BigDecimal;
import java.util.List;

import com.myprojects.admin.util.category.CategoryDTO;
import com.myprojects.admin.util.supplier.SupplierDTO;
import com.myprojects.admin.util.user.UserDTO;

public class OrderDTO {

	private Long orderId;
	private SupplierDTO supplierDTO;
	private String receivedDate;
	private List<OrderItemDTO> orderItemDTOList;

	private Integer count;
	private Integer quantityCount;
	private BigDecimal singlePrice;
	private BigDecimal totalPrice;
	private Integer status;
	private String statusDesc;
	private UserDTO userDTO;
	private String orderDate;
	private String updatedDate;
	private String name;
	private String description;
	private String size;
	private String manufactureCountry;
	private String brand;
	private String color;
	private CategoryDTO categoryDTO;
	private String itemDescription;

	private Long noteId;

	private String filterItemName;
	private Long filterCategoryId;
	private Long filterNotedUserId;
	private Long notedSupplier;

	private String supplierName;

	public SupplierDTO getSupplierDTO() {
		return supplierDTO;
	}

	public void setSupplierDTO(SupplierDTO supplierDTO) {
		this.supplierDTO = supplierDTO;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public List<OrderItemDTO> getOrderItemDTOList() {
		return orderItemDTOList;
	}

	public void setOrderItemDTOList(List<OrderItemDTO> orderItemDTOList) {
		this.orderItemDTOList = orderItemDTOList;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getManufactureCountry() {
		return manufactureCountry;
	}

	public void setManufactureCountry(String manufactureCountry) {
		this.manufactureCountry = manufactureCountry;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public String getFilterItemName() {
		return filterItemName;
	}

	public void setFilterItemName(String filterItemName) {
		this.filterItemName = filterItemName;
	}

	public Long getFilterCategoryId() {
		return filterCategoryId;
	}

	public void setFilterCategoryId(Long filterCategoryId) {
		this.filterCategoryId = filterCategoryId;
	}

	public Long getFilterNotedUserId() {
		return filterNotedUserId;
	}

	public void setFilterNotedUserId(Long filterNotedUserId) {
		this.filterNotedUserId = filterNotedUserId;
	}

	public Long getNotedSupplier() {
		return notedSupplier;
	}

	public void setNotedSupplier(Long notedSupplier) {
		this.notedSupplier = notedSupplier;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getQuantityCount() {
		return quantityCount;
	}

	public void setQuantityCount(Integer quantityCount) {
		this.quantityCount = quantityCount;
	}

}
