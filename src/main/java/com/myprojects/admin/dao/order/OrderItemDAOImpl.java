package com.myprojects.admin.dao.order;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.OrderItem;

@Repository
@Transactional
public class OrderItemDAOImpl extends GenericDaoImpl<OrderItem, Long> implements OrderItemDAO {

	@Override
	public Boolean checkOrderItemsExistOrNot(Long itemId) {
		Criteria c = getCurrentSession().createCriteria(OrderItem.class);
		c.createAlias("item", "i");
		c.add(Restrictions.eq("i.id", itemId));
		return CommonUtil.validList(c.list()) ? true : false;
	}

}
