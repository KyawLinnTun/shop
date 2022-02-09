package com.myprojects.admin.service.supplier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.cardnumber.CardNumberDAO;
import com.myprojects.admin.dao.supplier.SupplierDAO;
import com.myprojects.admin.dao.suppliercategory.SupplierCategoryDAO;
import com.myprojects.admin.entity.CardNumbers;
import com.myprojects.admin.entity.Category;
import com.myprojects.admin.entity.PaymentType;
import com.myprojects.admin.entity.Supplier;
import com.myprojects.admin.entity.SupplierCategory;
import com.myprojects.admin.util.cardnumber.CardNumberDTO;
import com.myprojects.admin.util.supplier.SupplierDTO;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDAO supplierDao;
	@Autowired
	private SupplierCategoryDAO scDAO;
	@Autowired
	private CardNumberDAO cnDAO;

	@Override
	@CheckMethod
	public SupplierDTO getById(Long id) {
		Supplier supplier = supplierDao.get(id);
		if (supplier != null) {
			SupplierDTO dto = new SupplierDTO(supplier);
			List<CardNumbers> cnList = cnDAO.getCardNumberBySupplierId(supplier.getId());
			if (CommonUtil.validList(cnList)) {
				dto.setCardNumberDTOList(new ArrayList<>());
				cnList.stream().forEach(cn -> {
					dto.getCardNumberDTOList().add(new CardNumberDTO(cn));
					dto.setCardNumberSizeList(cnList.size());
				});
			}
			return dto;
		}
		return null;
	}

	@Override
	@CheckMethod
	public SupplierDTO saveOrUpdateSupplier(SupplierDTO supplierDTO) {
		if (supplierDTO != null && CommonUtil.validString(supplierDTO.getName())
				&& CommonUtil.validList(supplierDTO.getSelectedCategoryDTOList())) {
			Supplier supplier = new Supplier();
			if (CommonUtil.validLong(supplierDTO.getId())) {
				supplier = supplierDao.get(supplierDTO.getId());
				List<SupplierCategory> scList = scDAO.getBySupplierId(supplierDTO.getId());
				if (CommonUtil.validList(scList)) {
					scList.stream().forEach(sc -> {
						scDAO.delete(sc);
					});
				}

				List<CardNumbers> cnList = cnDAO.getCardNumberBySupplierId(supplierDTO.getId());
				if (CommonUtil.validList(cnList)) {
					cnList.stream().forEach(cn -> {
						cnDAO.delete(cn);
					});
				}
			}
			supplier.setStatus(supplierDTO.getStatus());
			supplier.setName(supplierDTO.getName());
			if (CommonUtil.validList(supplierDTO.getPhoneNumbers())) {
				supplier.setPhoneNo("");
				for (String ph : supplierDTO.getPhoneNumbers()) {
					supplier.setPhoneNo(supplier.getPhoneNo().concat(ph));
					if (!ph.equalsIgnoreCase(
							supplierDTO.getPhoneNumbers().get(supplierDTO.getPhoneNumbers().size() - 1))) {
						supplier.setPhoneNo(supplier.getPhoneNo().concat(" , "));
					}

				}
			}
			supplier.setAddress(supplierDTO.getAddress());
			supplierDao.saveOrUpdate(supplier);

			for (Long catId : supplierDTO.getSelectedCategoryDTOList()) {
				SupplierCategory sc = new SupplierCategory();
				Category category = new Category();
				category.setId(catId);
				sc.setCategory(category);
				sc.setSupplier(supplier);
				sc.setCreatedTime(new Date());
				scDAO.save(sc);
			}
			if (CommonUtil.validList(supplierDTO.getCardNumberDTOList())) {
				for (CardNumberDTO dto : supplierDTO.getCardNumberDTOList()) {
					if (CommonUtil.validLong(dto.getPaymentTypeId()) && CommonUtil.validString(dto.getNo())) {
						CardNumbers cn = new CardNumbers();
						cn.setName(dto.getName());
						cn.setNo(dto.getNo());
						PaymentType pt = new PaymentType();
						pt.setId(dto.getPaymentTypeId());
						cn.setPaymentType(pt);
						cn.setCreatedTime(new Date());
						cn.setSupplier(supplier);
						cnDAO.save(cn);
					}
				}
			}

			return new SupplierDTO(supplier);
		}
		return null;
	}

	@Override
	@CheckMethod
	public List<SupplierDTO> searchSupplier(SupplierDTO dto, String language) throws Exception {
		List<SupplierDTO> supplierList = supplierDao.searchSupplier(dto);
		if (CommonUtil.validList(supplierList)) {
			for (SupplierDTO sup : supplierList) {
				List<SupplierCategory> suppCatList = scDAO.getBySupplierId(sup.getId());
				if (CommonUtil.validList(suppCatList)) {
					String categories = "";
					for (SupplierCategory sc : suppCatList) {
						if (CommonUtil.validString(language)) {
							categories += sc.getCategory().getMmFontName();
						} else {
							categories += sc.getCategory().getName();
						}
						if (!sc.getCategory().getId()
								.equals(suppCatList.get(suppCatList.size() - 1).getCategory().getId())) {
							categories += " , ";
						}
					}
					sup.setSelectedCategories(categories);
					sup.setCardNumberSizeList(suppCatList.size());
				}
			}
		}
		return supplierList;
	}

	@Override
	@CheckMethod
	public boolean deleteSupplierById(Long id) {
		try {
			Supplier supp = supplierDao.get(id);
			if (supp != null) {
				List<SupplierCategory> suppCatList = scDAO.getBySupplierId(id);
				if (CommonUtil.validList(suppCatList)) {
					suppCatList.stream().forEach(sc -> {
						scDAO.delete(sc);
					});
				}
				List<CardNumbers> cnList = cnDAO.getCardNumberBySupplierId(id);
				if (CommonUtil.validList(cnList)) {
					cnList.stream().forEach(cn -> {
						cnDAO.delete(cn);
					});
				}

				supplierDao.delete(supp);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@CheckMethod
	public List<SupplierDTO> getSupplierLabelAndIdByCategoryId(Long categoryId,String language) throws Exception {
		return scDAO.getSupplierLabelAndIdByCategoryId(categoryId,language);
	}

}
