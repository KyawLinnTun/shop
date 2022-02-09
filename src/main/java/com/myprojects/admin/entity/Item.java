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
import com.myprojects.admin.common.TableName;

@Entity
@Table(name = TableName.ITEM)
public class Item extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name")
	private String name;

	@Column(name = "Image")
	private String image;

	@Column(name = "Description")
	private String description;

	@Column(name = "Size")
	private String size;

	@Column(name = "ManufactureCountry")
	private String manufactureCountry;

	@Column(name = "Brand")
	private String brand;

	@Column(name = "Color")
	private String color;

	@Column(name = "Price")
	private BigDecimal price;

	@Column(name = "BuyingPrice")
	private BigDecimal buyPrice;

	@OneToOne
	@JoinColumn(name = "Category_Id")
	private Category category;

	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@OneToOne
	@JoinColumn(name = "CreatedUserId")
	private User createdUser;

	@OneToOne
	@JoinColumn(name = "UpdatedUserId")
	private User updatedUser;

	@Column(name = "Status")
	private Integer status;

	public Item() {
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}

	public User getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
