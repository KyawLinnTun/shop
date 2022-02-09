package com.myprojects.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonAjaxResponse;
import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.AdminPages;
import com.myprojects.admin.common.enumeration.CommonStatus;
import com.myprojects.admin.common.enumeration.MessageType;
import com.myprojects.admin.service.category.CategoryService;
import com.myprojects.admin.service.item.ItemService;
import com.myprojects.admin.service.order.OrderItemService;
import com.myprojects.admin.util.category.CategoryDTO;
import com.myprojects.admin.util.item.ItemDTO;
import com.myprojects.admin.util.user.UserDTO;

@Controller
public class ItemController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private OrderItemService orderItemService;

	@GetMapping("/item")
	@CheckMethod
	public String getItem(@RequestParam(name = "id", required = false) Long id, Model model, HttpServletRequest req) {
		ItemDTO itemDTO = new ItemDTO();
		if (CommonUtil.validLong(id)) {
			itemDTO = itemService.getById(id);
			model.addAttribute("showDeleteBtn", !orderItemService.checkOrderItemsExistOrNot(id));
		} else {
			model.addAttribute("showDeleteBtn", false);
		}
		model.addAttribute("itemDTO", itemDTO);
		commonModelList(model);
		commonModel(model, "labels.item_create_menu");
		return "item";
	}

	@PostMapping("/item")
	@CheckMethod
	public String postItem(@ModelAttribute("itemDTO") ItemDTO itemDTO, Model model, HttpServletRequest request) {
		try {
			UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CommonConstant.LOGIN_USER_SESSION_KEY);
			itemDTO.setCreatedUser(userDTO);
			itemDTO.setUpdatedUser(userDTO);
			ItemDTO dto = itemService.saveOrUpdateItem(itemDTO);
			if (dto == null && CommonUtil.validLong(itemDTO.getId())) {
				showMessage(model, MessageType.DANGER, "labels.update_fail_msg");
				model.addAttribute("itemDTO", itemDTO);
			} else if (CommonUtil.validLong(itemDTO.getId())) {
				showMessage(model, MessageType.SUCCESS, "labels.update_success_msg");
				model.addAttribute("itemDTO", new ItemDTO());
			}
			if (dto == null && !CommonUtil.validLong(itemDTO.getId())) {
				showMessage(model, MessageType.DANGER, "labels.save_fail_msg");
				model.addAttribute("itemDTO", itemDTO);
			} else if (!CommonUtil.validLong(itemDTO.getId())) {
				showMessage(model, MessageType.SUCCESS, "labels.save_success_msg");
				model.addAttribute("itemDTO", new ItemDTO());
			}
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.save_fail_msg");
		}
		commonModelList(model);
		commonModel(model, "labels.item_create_menu");
		return "item";
	}

	@GetMapping("/item-list")
	@CheckMethod
	public String getItemList(Model model, HttpServletRequest request) throws Exception {
		try {
			List<ItemDTO> itemList = itemService.searchItem(null);
			model.addAttribute("itemList", itemList);
			model.addAttribute("itemDTO", new ItemDTO());
			String delSup = request.getParameter("delSup");
			if (CommonUtil.validString(delSup) && delSup.equals("1")) {
				showMessage(model, MessageType.SUCCESS, "labels.delete_success_msg");
			} else if (CommonUtil.validString(delSup) && delSup.equals("2")) {
				showMessage(model, MessageType.DANGER, "labels.delete_fail_msg");
			}
			commonModelList(model);
			commonModel(model, "labels.item_list_menu");
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.system_error");
			throw e;
		}
		return "item-list";
	}

	@PostMapping("/item-list")
	@CheckMethod
	public String postItemList(@ModelAttribute("itemDTO") ItemDTO itemDTO, Model model, HttpServletRequest request) {
		try {
			List<ItemDTO> itemList = itemService.searchItem(itemDTO);
			model.addAttribute("itemList", itemList);
			model.addAttribute("itemDTO", itemDTO);
			commonModel(model, "labels.item_list_menu");
			commonModelList(model);
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.system_error");
		}
		return "item-list";
	}

	@PostMapping("/deleteItemById")
	@CheckMethod
	public String deleteItemById(HttpServletRequest req, Model model) {
		String response = "";
		try {
			Long id = Long.valueOf(req.getParameter("id"));
			if (!CommonUtil.validLong(id)) {
				response = "redirect:/item-list.html?delSup=2";
			} else {
				commonModel(model, "labels.item_list_menu");
				if (itemService.deleteItemById(id)) {
					response = "redirect:/item-list.html?delSup=1";
				} else {
					response = "redirect:/item-list.html?delSup=2";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.system_error");
		}
		return response;
	}

	@GetMapping("/checkDuplicateItem")
	@CheckMethod
	public @ResponseBody String checkDuplicateItem(Model model,
			@RequestParam(value = "itemId", required = false) Long itemId,
			@RequestParam(value = "itemName", required = false) String itemName, HttpServletRequest request) {

		CommonAjaxResponse resp = new CommonAjaxResponse();
		List<ItemDTO> duplicateList = new ArrayList<ItemDTO>();
		if (CommonUtil.validString(itemName)) {
			try {
				duplicateList = itemService.selectDuplicateItems(itemId, itemName,
						(String) request.getSession().getAttribute(CommonConstant.LANGUAGE));
			} catch (Exception e) {
				e.printStackTrace();
				resp.setSuccessMessage("labels.system_error");
			}
		} else {
			resp.setSuccessMessage("labels.enter_item_name_place_holder");
		}
		Gson g = new Gson();
		resp.setData(g.toJson(duplicateList));
		return g.toJson(resp);
	}

	@CheckMethod
	public void commonModelList(Model model) {
		List<CategoryDTO> categoryDTOList = categoryService.getALL();
		model.addAttribute("categoryDTOList", categoryDTOList);
		model.addAttribute("statusList", CommonStatus.values());
	}

	public void commonModel(Model model, String pageTitle) {
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("activePage", AdminPages.ITEM.getCode());
	}

	public void showMessage(Model model, MessageType messageType, String message) {
		model.addAttribute("msgType", messageType);
		model.addAttribute("message", message);
		model.addAttribute("showAlertDisplay", "block");
	}

}
