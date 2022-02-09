package com.myprojects.admin.dao.suppliercategory;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.SupplierCategory;
import com.myprojects.admin.util.supplier.SupplierDTO;

@Repository
@Transactional
public class SupplierCategoryDAOImpl extends GenericDaoImpl<SupplierCategory, Long> implements SupplierCategoryDAO {

	@SuppressWarnings("unchecked")
	@Override
	@CheckMethod
	public List<SupplierCategory> getBySupplierId(Long supplierId) {
		Criteria c = getCurrentSession().createCriteria(SupplierCategory.class);
		c.createAlias("supplier", "s", JoinType.INNER_JOIN);
		c.add(Restrictions.eq("s.id", supplierId));
		c.addOrder(Order.asc("createdTime"));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@CheckMethod
	public List<SupplierDTO> getSupplierLabelAndIdByCategoryId(Long categoryId, String language) throws SQLException{
		StringBuilder query = new StringBuilder();
		query.append("Select s.Id as id,concat(s.Name,' ('");
		if (CommonUtil.validString(language)) {
			query.append(",c.MMFontDescription");
		} else {
			query.append(", c.EngFontDescription");
		}
		query.append(",' )') as supplierLabel");
		query.append(" from supplier_category sc join supplier s on s.Id=sc.Supplier_Id");
		query.append(" join category c on c.id=sc.Category_Id");
		query.append(" where sc.Category_Id=:categoryId");
		query.append(" union all");
		query.append(" (select distinct s.Id,s.Name from supplier_category sc join supplier s on s.Id=sc.Supplier_Id");
		query.append(" where sc.Category_Id!=:categoryId and s.id not in(");
		query.append(" select s.Id from supplier_category sc join supplier s on s.Id=sc.Supplier_Id ");
		query.append(" where sc.Category_Id=:categoryId) order by s.Name)");
		SQLQuery q = getCurrentSession().createSQLQuery(query.toString()).addScalar("id", StandardBasicTypes.LONG)
				.addScalar("supplierLabel", StandardBasicTypes.STRING);
		q.setResultTransformer(Transformers.aliasToBean(SupplierDTO.class));
		q.setParameter("categoryId", categoryId);
		return q.list();
	}

	@Override
	public boolean checkSupplierCategory(Long supplierId, Long categoryId) {
		Criteria c = getCurrentSession().createCriteria(SupplierCategory.class);
		c.createAlias("supplier", "s", JoinType.INNER_JOIN);
		c.add(Restrictions.eq("s.id", supplierId));
		c.createAlias("category", "c", JoinType.INNER_JOIN);
		c.add(Restrictions.eq("c.id", categoryId));
		return CommonUtil.validList(c.list()) ? true : false;
	}

}
