package com.myprojects.admin.dao.category;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.Category;

@Repository
@Transactional
public class CategoryDAOImpl extends GenericDaoImpl<Category, Long> implements CategoryDAO {

}
