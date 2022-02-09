package com.myprojects.admin.dao.order;

import com.myprojects.admin.dao.generic.GenericDao;
import com.myprojects.admin.entity.OrderItem;

public interface OrderItemDAO extends GenericDao<OrderItem, Long> {

	Boolean checkOrderItemsExistOrNot(Long itemId);

}
