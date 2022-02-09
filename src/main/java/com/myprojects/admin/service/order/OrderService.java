package com.myprojects.admin.service.order;

import java.util.List;

import com.myprojects.admin.util.order.OrderDTO;

public interface OrderService {

	Long saveOrder(OrderDTO orderDTO);

	List<OrderDTO> getUpcomingOrders() throws Exception;

}
