package com.myprojects.admin.dao.cardnumber;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.CardNumbers;

@Repository
@Transactional
public class CardNumberDAOImpl extends GenericDaoImpl<CardNumbers, Long> implements CardNumberDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CardNumbers> getCardNumberBySupplierId(Long supplierId) {
		Criteria c = getCurrentSession().createCriteria(CardNumbers.class);
		c.createAlias("supplier", "s", JoinType.INNER_JOIN);
		c.add(Restrictions.eq("s.id", supplierId));
		return c.list();
	}

}
