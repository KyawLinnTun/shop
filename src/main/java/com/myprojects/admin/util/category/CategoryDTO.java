package com.myprojects.admin.util.category;

import com.myprojects.admin.entity.Category;

public class CategoryDTO {

	private Long id;
	private String name;
	private String mmFontName;
	private String engFontDesctiption;
	private String mmFontDesctiption;
	private String image;
	private boolean selectedFacility;

	public CategoryDTO() {
	}

	public CategoryDTO(Category cat) {
		super();
		this.id = cat.getId();
		this.name = cat.getName();
		this.mmFontName = cat.getMmFontName();
		this.engFontDesctiption = cat.getEngFontDesctiption();
		this.mmFontDesctiption = cat.getMmFontDesctiption();
		this.image = cat.getImage();
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

	public String getMmFontName() {
		return mmFontName;
	}

	public void setMmFontName(String mmFontName) {
		this.mmFontName = mmFontName;
	}

	public String getEngFontDesctiption() {
		return engFontDesctiption;
	}

	public void setEngFontDesctiption(String engFontDesctiption) {
		this.engFontDesctiption = engFontDesctiption;
	}

	public String getMmFontDesctiption() {
		return mmFontDesctiption;
	}

	public void setMmFontDesctiption(String mmFontDesctiption) {
		this.mmFontDesctiption = mmFontDesctiption;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isSelectedFacility() {
		return selectedFacility;
	}

	public void setSelectedFacility(boolean selectedFacility) {
		this.selectedFacility = selectedFacility;
	}

}
