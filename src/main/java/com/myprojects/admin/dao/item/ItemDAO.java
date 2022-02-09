package com.myprojects.admin.dao.item;

import java.util.List;

import com.myprojects.admin.dao.generic.GenericDao;
import com.myprojects.admin.entity.Item;
import com.myprojects.admin.util.item.ItemDTO;

public interface ItemDAO extends GenericDao<Item, Long> {

	Item getItemByCriterias(String itemName, String size, String color, String manufactureCountry, String brand,
			Long categoryId);

	List<Item> searchItem(ItemDTO itemDTO);

	List<ItemDTO> selectDuplicateItems(Long itemId, String itemName, String language) throws Exception;

}
