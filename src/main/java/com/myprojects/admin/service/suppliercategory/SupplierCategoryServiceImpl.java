package com.myprojects.admin.service.suppliercategory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.suppliercategory.SupplierCategoryDAO;
import com.myprojects.admin.entity.SupplierCategory;
import com.myprojects.admin.util.category.CategoryDTO;

@Service
public class SupplierCategoryServiceImpl implements SupplierCategoryService {

	@Autowired
	private SupplierCategoryDAO scDAO;

	@Override
	public List<CategoryDTO> getCategoryDTOBySupplierId(Long supplierId) {
		List<SupplierCategory> scList = scDAO.getBySupplierId(supplierId);
		List<CategoryDTO> dtoList = new ArrayList<>();
		if (CommonUtil.validList(scList)) {
			scList.stream().forEach(sc -> {
				dtoList.add(new CategoryDTO(sc.getCategory()));
			});
		}
		return dtoList;
	}

}
