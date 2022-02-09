package com.myprojects.admin.util.supplier;

import java.util.ArrayList;
import java.util.List;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.CommonStatus;
import com.myprojects.admin.entity.Supplier;
import com.myprojects.admin.util.cardnumber.CardNumberDTO;

public class SupplierDTO {

	private Long id;
	private String name;
	private String supplierLabel;
	private String phoneNo;
	private String tempPh;
	private List<String> phoneNumbers;
	private List<String> phNoList;
	private String address;
	private Integer status;
	private String statusDesc;
	private List<Long> selectedCategoryDTOList = new ArrayList<>();
	private List<CardNumberDTO> cardNumberDTOList;
	private Integer cardNumberSizeList;

	private String selectedCategories;
	private Long categoryId;

	public SupplierDTO() {
	}

	public SupplierDTO(Long id, String name, String phoneNo, String address, Integer status, String statusDesc) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.address = address;
		this.status = status;
		this.statusDesc = statusDesc;
	}

	@CheckMethod
	public SupplierDTO(Supplier supplier) {
		super();
		this.id = supplier.getId();
		this.name = supplier.getName();
		this.phoneNo = supplier.getPhoneNo();
		this.phoneNumbers = new ArrayList<>();
		if (CommonUtil.validString(phoneNo)) {
			phNoList = new ArrayList<>();
			String[] phStrs = phoneNo.trim().split(",");
			for (String ph : phStrs) {
				phoneNumbers.add(ph);
				phNoList.add(ph);
			}
		}
		this.address = supplier.getAddress();
		this.status = supplier.getStatus();
		this.statusDesc = CommonStatus.getDescByCode(supplier.getStatus());
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CardNumberDTO> getCardNumberDTOList() {
		return cardNumberDTOList;
	}

	public void setCardNumberDTOList(List<CardNumberDTO> cardNumberDTOList) {
		this.cardNumberDTOList = cardNumberDTOList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCardNumberSizeList() {
		return cardNumberSizeList;
	}

	public void setCardNumberSizeList(Integer cardNumberSizeList) {
		this.cardNumberSizeList = cardNumberSizeList;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getTempPh() {
		return tempPh;
	}

	public void setTempPh(String tempPh) {
		this.tempPh = tempPh;
	}

	public List<String> getPhNoList() {
		return phNoList;
	}

	public void setPhNoList(List<String> phNoList) {
		this.phNoList = phNoList;
	}

	public List<Long> getSelectedCategoryDTOList() {
		return selectedCategoryDTOList;
	}

	public void setSelectedCategoryDTOList(List<Long> selectedCategoryDTOList) {
		this.selectedCategoryDTOList = selectedCategoryDTOList;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(String selectedCategories) {
		this.selectedCategories = selectedCategories;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getSupplierLabel() {
		return supplierLabel;
	}

	public void setSupplierLabel(String supplierLabel) {
		this.supplierLabel = supplierLabel;
	}

}
