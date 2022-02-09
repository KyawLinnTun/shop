package com.myprojects.admin.service.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.category.CategoryDAO;
import com.myprojects.admin.entity.Category;
import com.myprojects.admin.util.category.CategoryDTO;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO dao;

	@Override
	@CheckMethod
	public List<CategoryDTO> getALL() {
		List<CategoryDTO> dtoList = new ArrayList<>();
		List<Category> catList = dao.getAll();
		if (CommonUtil.validList(catList)) {
			catList.stream().forEach(cat -> {
				dtoList.add(new CategoryDTO(cat));
			});
		}
		return dtoList;
	}

	@Override
	public CategoryDTO getCategoryById(Long categoryId) {
		Category category=dao.get(categoryId);
		if(category!=null) {
			return new CategoryDTO(category);
		}
		return null;
	}

}
