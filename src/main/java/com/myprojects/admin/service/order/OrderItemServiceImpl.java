package com.myprojects.admin.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.dao.order.OrderItemDAO;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemDAO orderItemDao;

	@Override
	public Boolean checkOrderItemsExistOrNot(Long itemId) {
		return orderItemDao.checkOrderItemsExistOrNot(itemId);
	}
}
