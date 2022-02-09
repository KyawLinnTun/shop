package com.myprojects.admin.util.note;

import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.entity.Note;
import com.myprojects.admin.util.category.CategoryDTO;
import com.myprojects.admin.util.supplier.SupplierDTO;
import com.myprojects.admin.util.user.UserDTO;

public class NoteDTO {

	private Long id;
	private String itemName;
	private String size;
	private String color;
	private Integer remaining;
	private String manufactureCountry;
	private String brand;
	private CategoryDTO categoryDTO;
	private String description;
	private UserDTO notedUser;
	private String createdDate;

	private SupplierDTO supplierDTO;

	public NoteDTO() {
	}

	public NoteDTO(Note note) {
		super();
		this.id = note.getId();
		this.itemName = note.getItemName();
		this.size = note.getSize();
		this.color = note.getColor();
		this.remaining = note.getRemaining();
		this.manufactureCountry = note.getManufactureCountry();
		this.brand = note.getBrand();
		this.categoryDTO = note.getCategory() != null && CommonUtil.validLong(note.getCategory().getId())
				? new CategoryDTO(note.getCategory())
				: null;
		this.description = note.getDescription();
		this.notedUser = note.getNotedUser() != null && CommonUtil.validLong(note.getNotedUser().getId())
				? new UserDTO(note.getNotedUser())
				: null;
		this.createdDate = CommonUtil.changeDateToString(CommonConstant.STD_DATE_TIME_FORMAT, note.getCreatedDate());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDTO getNotedUser() {
		return notedUser;
	}

	public void setNotedUser(UserDTO notedUser) {
		this.notedUser = notedUser;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
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

	public SupplierDTO getSupplierDTO() {
		return supplierDTO;
	}

	public void setSupplierDTO(SupplierDTO supplierDTO) {
		this.supplierDTO = supplierDTO;
	}

}
