package com.myprojects.admin.service.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.CommonStatus;
import com.myprojects.admin.dao.item.ItemDAO;
import com.myprojects.admin.dao.note.NoteDAO;
import com.myprojects.admin.dao.order.OrderDAO;
import com.myprojects.admin.dao.order.OrderItemDAO;
import com.myprojects.admin.dao.suppliercategory.SupplierCategoryDAO;
import com.myprojects.admin.entity.Category;
import com.myprojects.admin.entity.Item;
import com.myprojects.admin.entity.Order;
import com.myprojects.admin.entity.OrderItem;
import com.myprojects.admin.entity.Supplier;
import com.myprojects.admin.entity.SupplierCategory;
import com.myprojects.admin.entity.User;
import com.myprojects.admin.util.order.OrderDTO;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private ItemDAO itemDao;
	@Autowired
	private OrderItemDAO orderItemDao;
	@Autowired
	private SupplierCategoryDAO supplierCategoryDAO;
	@Autowired
	private NoteDAO noteDAO;

	@Override
	@CheckMethod
	public Long saveOrder(OrderDTO orderDTO) {
		if (orderDTO != null) {
			Item item = new Item();
			Item result = itemDao.getItemByCriterias(orderDTO.getName(), orderDTO.getSize(), orderDTO.getColor(),
					orderDTO.getManufactureCountry(), orderDTO.getBrand(), orderDTO.getCategoryDTO().getId());
			if (result != null && CommonUtil.validLong(result.getId())) {
				item = result;
				item.setUpdatedUser(orderDTO.getUserDTO() != null && CommonUtil.validLong(orderDTO.getUserDTO().getId())
						? new User(orderDTO.getUserDTO().getId())
						: null);
			} else {
				item.setCreatedUser(orderDTO.getUserDTO() != null && CommonUtil.validLong(orderDTO.getUserDTO().getId())
						? new User(orderDTO.getUserDTO().getId())
						: null);
			}
			item.setName(orderDTO.getName());
			item.setDescription(orderDTO.getItemDescription());
			item.setSize(orderDTO.getSize());
			item.setManufactureCountry(orderDTO.getManufactureCountry());
			item.setBrand(orderDTO.getBrand());
			item.setColor(orderDTO.getColor());
			item.setBuyPrice(orderDTO.getSinglePrice());
			item.setCategory(
					orderDTO.getCategoryDTO() != null && CommonUtil.validLong(orderDTO.getCategoryDTO().getId())
							? new Category(orderDTO.getCategoryDTO().getId())
							: null);
			item.setUpdatedDate(new Date());
			item.setStatus(CommonStatus.ACTIVE.getCode());
			itemDao.saveOrUpdate(item);

			Order order = orderDao.getOrderByfilter(orderDTO.getSupplierDTO().getId(),
					CommonUtil.changeStringToDate(CommonConstant.STD_DATE_FORMAT, orderDTO.getReceivedDate()));
			if (order == null || !CommonUtil.validLong(order.getId())) {
				order = new Order(orderDTO);
				orderDao.saveOrUpdate(order);
			}

			OrderItem orderItem = new OrderItem(orderDTO);
			orderItem.setItem(item);
			orderItem.setOrder(order);
			orderItemDao.saveOrUpdate(orderItem);

			if (!supplierCategoryDAO.checkSupplierCategory(orderDTO.getSupplierDTO().getId(),
					orderDTO.getCategoryDTO().getId())) {
				SupplierCategory sc = new SupplierCategory();
				Supplier supplier = new Supplier();
				supplier.setId(orderDTO.getSupplierDTO().getId());
				sc.setSupplier(supplier);

				Category category = new Category();
				category.setId(orderDTO.getCategoryDTO().getId());
				sc.setCategory(category);
				supplierCategoryDAO.saveOrUpdate(sc);
			}
			if (CommonUtil.validLong(orderDTO.getNoteId())) {
				noteDAO.delete(noteDAO.get(orderDTO.getNoteId()));
			}

			return order.getId();
		}
		return null;
	}

	@Override
	public List<OrderDTO> getUpcomingOrders() throws Exception {
		return orderDao.getUpcomingOrders();
	}
}
