package com.myprojects.admin.service.suppliercategory;

import java.util.List;

import com.myprojects.admin.util.category.CategoryDTO;

public interface SupplierCategoryService {

	List<CategoryDTO> getCategoryDTOBySupplierId(Long supplierId);

}
