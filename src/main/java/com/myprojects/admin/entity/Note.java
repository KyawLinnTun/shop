package com.myprojects.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myprojects.admin.common.CommonEntity;
import com.myprojects.admin.common.TableName;

@Entity
@Table(name = TableName.NOTE)
public class Note extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ItemName")
	private String itemName;

	@Column(name = "Size")
	private String size;

	@Column(name = "Color")
	private String color;

	@Column(name = "Remaining")
	private Integer remaining;

	@Column(name = "ManufactureCountry")
	private String manufactureCountry;

	@Column(name = "Brand")
	private String brand;

	@OneToOne
	@JoinColumn(name = "Category_Id")
	private Category category;

	@Column(name = "Description")
	private String description;

	@OneToOne
	@JoinColumn(name = "NotedUser_Id")
	private User notedUser;

	@Column(name = "CreatedDate")
	private Date createdDate;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getNotedUser() {
		return notedUser;
	}

	public void setNotedUser(User notedUser) {
		this.notedUser = notedUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

}
