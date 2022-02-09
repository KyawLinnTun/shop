package com.myprojects.admin.dao.suppliercategory;

import java.sql.SQLException;
import java.util.List;

import com.myprojects.admin.dao.generic.GenericDao;
import com.myprojects.admin.entity.SupplierCategory;
import com.myprojects.admin.util.supplier.SupplierDTO;

public interface SupplierCategoryDAO extends GenericDao<SupplierCategory, Long> {

	List<SupplierCategory> getBySupplierId(Long supplierId);

	List<SupplierDTO> getSupplierLabelAndIdByCategoryId(Long categoryId, String language) throws SQLException;

	boolean checkSupplierCategory(Long id, Long id2);

}
