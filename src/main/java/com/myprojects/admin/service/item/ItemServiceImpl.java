package com.myprojects.admin.service.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.ImagesUtil;
import com.myprojects.admin.dao.item.ItemDAO;
import com.myprojects.admin.entity.Category;
import com.myprojects.admin.entity.Item;
import com.myprojects.admin.entity.User;
import com.myprojects.admin.util.item.ItemDTO;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDAO itemDAO;

	@Override
	@CheckMethod
	public ItemDTO getItemByCriterias(String itemName, String size, String color, String manufactureCountry,
			String brand, Long categoryId) {
		Item item = itemDAO.getItemByCriterias(itemName, size, color, manufactureCountry, brand, categoryId);
		if (item != null) {
			return new ItemDTO(item);
		}
		return null;
	}

	@Override
	@CheckMethod
	public ItemDTO getById(Long id) {
		Item item = itemDAO.get(id);
		if (item != null) {
			return new ItemDTO(item);
		}
		return null;
	}

	@Override
	@CheckMethod
	public ItemDTO saveOrUpdateItem(ItemDTO itemDTO) throws Exception {
		if (itemDTO != null) {
			Item item = new Item();
			User user = new User();
			if (CommonUtil.validLong(itemDTO.getId())) {
				item = itemDAO.get(itemDTO.getId());
				user.setId(itemDTO.getUpdatedUser().getId());
				item.setUpdatedUser(user);
			} else {
				user.setId(itemDTO.getCreatedUser().getId());
				item.setCreatedUser(user);
			}
			item.setUpdatedDate(new Date());
			item.setName(itemDTO.getName());
			item.setDescription(itemDTO.getDescription());
			item.setSize(itemDTO.getSize());
			item.setManufactureCountry(itemDTO.getManufactureCountry());
			item.setBrand(itemDTO.getBrand());
			item.setColor(itemDTO.getColor());
			item.setPrice(itemDTO.getPrice());
			item.setBuyPrice(itemDTO.getBuyPrice());
			item.setStatus(itemDTO.getStatus());
			if (itemDTO.getCategoryDTO() != null && CommonUtil.validLong(itemDTO.getCategoryDTO().getId())) {
				Category category = new Category();
				category.setId(itemDTO.getCategoryDTO().getId());
				item.setCategory(category);
			}
			item.setImage(itemDTO.getImage());
			itemDAO.saveOrUpdate(item);
			if (itemDTO.getImageFile() != null && !itemDTO.getImageFile().getOriginalFilename().isEmpty()) {
				String savedFileName = ImagesUtil.uploadMultipartFile(itemDTO.getImageFile(),
						CommonConstant.ITEM_DIRECTORY + CommonConstant.ITEM_IMAGE_DIRECTORY + item.getId() + "/",
						CommonConstant.ITEM_IMAGE_PREFIX, item.getId());
				item.setImage(savedFileName);
				itemDTO.setImage(savedFileName);
				itemDAO.merge(item);
			}
			return new ItemDTO(item);
		}
		return null;
	}

	@Override
	@CheckMethod
	public boolean deleteItemById(Long id) {
		if (CommonUtil.validLong(id)) {
			itemDAO.delete(itemDAO.get(id));
			return true;
		}
		return false;
	}

	@Override
	@CheckMethod
	public List<ItemDTO> searchItem(ItemDTO itemDTO) {
		List<Item> itemList = itemDAO.searchItem(itemDTO);
		List<ItemDTO> dtoList = new ArrayList<>();
		if (CommonUtil.validList(itemList)) {
			itemList.stream().forEach(item -> {
				dtoList.add(new ItemDTO(item));
			});
		}
		return dtoList;
	}

	@Override
	public List<ItemDTO> selectDuplicateItems(Long itemId, String itemName, String language) throws Exception {
		return itemDAO.selectDuplicateItems(itemId, itemName, language);
	}

}
