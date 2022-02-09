package com.myprojects.admin.service.item;

import java.util.List;

import com.myprojects.admin.util.item.ItemDTO;

public interface ItemService {

	ItemDTO getItemByCriterias(String itemName, String size, String color, String manufactureCountry, String brand,
			Long categoryId);

	ItemDTO getById(Long id);

	ItemDTO saveOrUpdateItem(ItemDTO itemDTO) throws Exception;

	boolean deleteItemById(Long id);

	List<ItemDTO> searchItem(ItemDTO itemDTO);

	List<ItemDTO> selectDuplicateItems(Long itemId, String itemName, String language) throws Exception;

}
