package com.myprojects.admin.dao.supplier;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.Supplier;
import com.myprojects.admin.util.supplier.SupplierDTO;

@Repository
@Transactional
public class SupplierDAOImpl extends GenericDaoImpl<Supplier, Long> implements SupplierDAO {

	@SuppressWarnings("unchecked")
	@Override
	@CheckMethod
	public List<SupplierDTO> searchSupplier(SupplierDTO dto) throws Exception{
		StringBuilder query = new StringBuilder();
		query.append("Select s.Id as id, s.Name as name,s.PhoneNo as phoneNo,s.Address as address,s.status as status");
		query.append(",case when s.status=1 then 'Active' else 'Inactive' end as statusDesc");
		query.append(" from supplier s join supplier_category sc on sc.Supplier_Id=s.Id");
		query.append(" where s.name is not null");
		if (dto != null) {
			if (CommonUtil.validString(dto.getName())) {
				query.append(" And s.Name like '%" + dto.getName() + "%'");
			}
			if (CommonUtil.validString(dto.getPhoneNo())) {
				query.append(" And s.PhoneNo like '%" + dto.getPhoneNo() + "%'");
			}
			if (CommonUtil.validLong(dto.getCategoryId())) {
				query.append(" And sc.Category_Id = " + dto.getCategoryId());
			}
			if (CommonUtil.validInteger(dto.getStatus())) {
				query.append(" And s.Status=" + dto.getStatus());
			}
		}
		query.append(" Group by s.Id,s.Name,s.PhoneNo,s.Address,s.status");
		query.append(" Order by s.Name");
		SQLQuery q = getCurrentSession().createSQLQuery(query.toString()).addScalar("id", StandardBasicTypes.LONG)
				.addScalar("name", StandardBasicTypes.STRING).addScalar("phoneNo", StandardBasicTypes.STRING)
				.addScalar("address", StandardBasicTypes.STRING).addScalar("status", StandardBasicTypes.INTEGER)
				.addScalar("statusDesc", StandardBasicTypes.STRING);
		q.setResultTransformer(Transformers.aliasToBean(SupplierDTO.class));
		return q.list();
	}

}
