package com.myprojects.admin.dao.order;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.dao.generic.GenericDaoImpl;
import com.myprojects.admin.entity.Order;
import com.myprojects.admin.util.order.OrderDTO;

@Repository
@Transactional
public class OrderDAOImpl extends GenericDaoImpl<Order, Long> implements OrderDAO {

	@Override
	@CheckMethod
	public Order getOrderByfilter(Long supplierId, Date receivedDate) {
		Criteria c = getCurrentSession().createCriteria(Order.class);
		if (CommonUtil.validLong(supplierId)) {
			c.createAlias("supplier", "s");
			c.add(Restrictions.eq("s.id", supplierId));
		}
		if (receivedDate != null) {
			c.add(Restrictions.eq("receivedDate", receivedDate));
		}
		return (Order) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@CheckMethod
	public List<OrderDTO> getUpcomingOrders() throws Exception {
		StringBuilder query = new StringBuilder();
		query.append(
				"select o.Id as orderId,s.Name as supplierName,SUM(oi.TotalPrice) as totalPrice,SUM(oi.Count) as quantityCount,Count(oi.Id) as count");
		query.append(" from orderInfo o join orderitem oi on o.id=oi.Order_Id join supplier s on s.id=o.supplier_id");
		query.append(
				" where o.receivedDate=current_date() and oi.Id in (select oitem.Id from orderitem oitem where o.id=oitem.Order_Id and oitem.Status=1)");
		query.append(" group by o.Id ,s.Name order by s.Name");
		Query q = this.getCurrentSession().createSQLQuery(query.toString())
				.addScalar("orderId", StandardBasicTypes.LONG).addScalar("supplierName", StandardBasicTypes.STRING)
				.addScalar("totalPrice", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("quantityCount", StandardBasicTypes.INTEGER).addScalar("count", StandardBasicTypes.INTEGER);
		q.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		List<OrderDTO> orderDTOList = q.list();
		return orderDTOList;
	}

}
