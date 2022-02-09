package com.myprojects.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myprojects.admin.common.CommonEntity;
import com.myprojects.admin.common.TableName;

@Entity
@Table(name = TableName.CATEGORY)
public class Category extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name")
	private String name;
	@Column(name = "MMFontName")
	private String mmFontName;
	@Column(name = "EngFontDescription")
	private String engFontDesctiption;
	@Column(name = "MMFontDescription")
	private String mmFontDesctiption;
	@Column(name = "Image")
	private String image;

	public Category() {
	}

	public Category(Long categoryId) {
		setId(categoryId);
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

}
