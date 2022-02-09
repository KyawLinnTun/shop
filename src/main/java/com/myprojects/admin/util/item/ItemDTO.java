package com.myprojects.admin.util.item;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.CommonStatus;
import com.myprojects.admin.entity.Item;
import com.myprojects.admin.util.category.CategoryDTO;
import com.myprojects.admin.util.user.UserDTO;

public class ItemDTO {

	private Long id;
	private String name;
	private String image;
	private String description;
	private String size;
	private String manufactureCountry;
	private String brand;
	private String color;
	private CategoryDTO categoryDTO;
	private BigDecimal price;
	private BigDecimal buyPrice;
	private String updatedDate;
	private UserDTO createdUser;
	private UserDTO updatedUser;
	private Integer status;
	private String statusDesc;
	private String categoryName;

	private String sourceName;
	private MultipartFile imageFile;

	public ItemDTO() {
	}

	public ItemDTO(Item item) {
		super();
		this.id = item.getId();
		this.name = item.getName();
		this.image = item.getImage();
		this.description = item.getDescription();
		this.size = item.getSize();
		this.manufactureCountry = item.getManufactureCountry();
		this.brand = item.getBrand();
		this.color = item.getColor();
		this.price = item.getPrice();
		this.buyPrice = item.getBuyPrice();
		this.categoryDTO = item.getCategory() != null ? new CategoryDTO(item.getCategory()) : null;
		this.updatedDate = item.getUpdatedDate() != null
				? CommonUtil.changeDateToString(CommonConstant.STD_DATE_TIME_FORMAT, item.getUpdatedDate())
				: null;
		this.createdUser = item.getCreatedUser() != null ? new UserDTO(item.getCreatedUser()) : null;
		this.updatedUser = item.getUpdatedUser() != null ? new UserDTO(item.getUpdatedUser()) : null;
		if (CommonUtil.validString(item.getImage())) {
			String[] names = item.getImage().split("/");
			this.sourceName = names.length > 0 ? names[names.length - 1] : "";
		}
		this.status = item.getStatus();
		this.statusDesc = CommonUtil.validInteger(item.getStatus()) ? CommonStatus.getDescByCode(item.getStatus()) : "";
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

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public UserDTO getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(UserDTO createdUser) {
		this.createdUser = createdUser;
	}

	public UserDTO getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(UserDTO updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
