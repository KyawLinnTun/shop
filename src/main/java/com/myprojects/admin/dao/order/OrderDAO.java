package com.myprojects.admin.dao.order;

import java.util.Date;
import java.util.List;

import com.myprojects.admin.dao.generic.GenericDao;
import com.myprojects.admin.entity.Order;
import com.myprojects.admin.util.order.OrderDTO;

public interface OrderDAO extends GenericDao<Order, Long> {

	Order getOrderByfilter(Long id, Date receivedDate);

	List<OrderDTO> getUpcomingOrders()throws Exception;

}
