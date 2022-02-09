package com.myprojects.admin.service.category;

import java.util.List;

import com.myprojects.admin.util.category.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getALL();

	CategoryDTO getCategoryById(Long categoryId);

}
