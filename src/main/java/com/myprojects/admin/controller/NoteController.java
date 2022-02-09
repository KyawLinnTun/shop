package com.myprojects.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.AdminPages;
import com.myprojects.admin.common.enumeration.CommonStatus;
import com.myprojects.admin.common.enumeration.MessageType;
import com.myprojects.admin.service.category.CategoryService;
import com.myprojects.admin.service.note.NoteService;
import com.myprojects.admin.service.order.OrderService;
import com.myprojects.admin.service.supplier.SupplierService;
import com.myprojects.admin.service.user.UserService;
import com.myprojects.admin.util.category.CategoryDTO;
import com.myprojects.admin.util.note.NoteDTO;
import com.myprojects.admin.util.order.OrderDTO;
import com.myprojects.admin.util.supplier.SupplierDTO;
import com.myprojects.admin.util.user.UserDTO;

@Controller
public class NoteController {

	@Autowired
	private NoteService noteService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SupplierService supplierService;

	@GetMapping("/note")
	@CheckMethod
	public String getNote(@RequestParam(name = "id", required = false) Long id, Model model, HttpServletRequest req) {
		NoteDTO noteDTO = new NoteDTO();
		if (CommonUtil.validLong(id)) {
			noteDTO = noteService.getById(id);
		}
		model.addAttribute("noteDTO", noteDTO);
		commonModelList(model);
		commonModel(model, "labels.note_create_menu");
		return "note";
	}

	@PostMapping("/note")
	@CheckMethod
	public String postNote(@ModelAttribute("noteDTO") NoteDTO noteDTO, Model model, HttpServletRequest request) {
		try {
			UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CommonConstant.LOGIN_USER_SESSION_KEY);
			noteDTO.setNotedUser(userDTO);
			NoteDTO dto = noteService.saveOrUpdateNote(noteDTO);
			if (dto == null && CommonUtil.validLong(noteDTO.getId())) {
				showMessage(model, MessageType.DANGER, "labels.update_fail_msg");
				model.addAttribute("noteDTO", noteDTO);
			} else if (CommonUtil.validLong(noteDTO.getId())) {
				showMessage(model, MessageType.SUCCESS, "labels.update_success_msg");
				model.addAttribute("noteDTO", new NoteDTO());
			}
			if (dto == null && !CommonUtil.validLong(noteDTO.getId())) {
				showMessage(model, MessageType.DANGER, "labels.save_fail_msg");
				model.addAttribute("noteDTO", noteDTO);
			} else if (!CommonUtil.validLong(noteDTO.getId())) {
				showMessage(model, MessageType.SUCCESS, "labels.save_success_msg");
				model.addAttribute("noteDTO", new NoteDTO());
			}
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.save_fail_msg");
		}
		commonModelList(model);
		commonModel(model, "labels.note_create_menu");
		return "note";
	}

	@GetMapping("/note-list")
	@CheckMethod
	public String getNoteList(Model model, HttpServletRequest request) {
		try {
			NoteDTO noteDTO = new NoteDTO();
			String delSup = request.getParameter("delSup");
			if (CommonUtil.validString(delSup) && delSup.equals("1")) {
				showMessage(model, MessageType.SUCCESS, "labels.delete_success_msg");
			} else if (CommonUtil.validString(delSup) && delSup.equals("2")) {
				showMessage(model, MessageType.DANGER, "labels.delete_fail_msg");
			}
			String addOrder = request.getParameter("addOrder");
			if (CommonUtil.validString(addOrder)) {
				if (addOrder.equals("1")) {
					showMessage(model, MessageType.WARNING, "labels.order_information_not_complete_msg");
				} else if (addOrder.equals("2")) {
					showMessage(model, MessageType.DANGER, "labels.item_does_not_exist_msg");
				} else if (addOrder.equals("3")) {
					showMessage(model, MessageType.SUCCESS, "labels.save_success_msg");
				} else if (addOrder.equals("4")) {
					showMessage(model, MessageType.DANGER, "labels.save_fail_msg");
				} else if (addOrder.equals("5")) {
					showMessage(model, MessageType.DANGER, "labels.system_error");
				}
			}
			noteDTO.setItemName(request.getParameter("itemName"));
			if (CommonUtil.validString(request.getParameter("categoryId"))) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setId(Long.valueOf(request.getParameter("categoryId")));
				noteDTO.setCategoryDTO(categoryDTO);
			}
			if (CommonUtil.validString(request.getParameter("notedUserId"))) {
				UserDTO userDTO = new UserDTO();
				userDTO.setId(Long.valueOf(request.getParameter("notedUserId")));
				noteDTO.setNotedUser(userDTO);
			}
			if (CommonUtil.validString(request.getParameter("notedSupplierId"))) {
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setId(Long.valueOf(request.getParameter("notedSupplierId")));
				noteDTO.setSupplierDTO(supplierDTO);
			}
			List<NoteDTO> noteList = noteService.searchNote(noteDTO);
			model.addAttribute("noteList", noteList);
			model.addAttribute("noteDTO", noteDTO);
			model.addAttribute("orderDTO", new OrderDTO());
			commonModelList(model);
			model.addAttribute("statusList", CommonStatus.values());
			commonModel(model, "labels.note_list_menu");
			List<SupplierDTO> supplierList = supplierService.searchSupplier(null,
					(String) request.getSession().getAttribute(CommonConstant.LANGUAGE));
			model.addAttribute("supplierList", supplierList);
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.system_error");
		}
		return "note-list";
	}

	@PostMapping("/note-list")
	@CheckMethod
	public String postMapping(@ModelAttribute("noteDTO") NoteDTO noteDTO, Model model, HttpServletRequest request) {
		try {
			List<NoteDTO> noteList = noteService.searchNote(noteDTO);
			model.addAttribute("noteList", noteList);
			model.addAttribute("noteDTO", noteDTO);
			model.addAttribute("orderDTO", new OrderDTO());

			List<SupplierDTO> supplierList = supplierService.searchSupplier(null,
					(String) request.getSession().getAttribute(CommonConstant.LANGUAGE));
			model.addAttribute("supplierList", supplierList);

			commonModel(model, "labels.note_list_menu");
			commonModelList(model);
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.system_error");
		}
		return "note-list";
	}

	@PostMapping("/deleteNoteById")
	@CheckMethod
	public String deleteDoctorById(HttpServletRequest req, Model model) {
		String response = "";
		try {
			Long id = Long.valueOf(req.getParameter("id"));
			if (!CommonUtil.validLong(id)) {
				response = "redirect:/note-list.html?delSup=2";
			} else {
				commonModel(model, "labels.note_list_menu");
				if (noteService.deleteNoteById(id)) {
					response = "redirect:/note-list.html?delSup=1";
				} else {
					response = "redirect:/note-list.html?delSup=2";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.system_error");
		}
		return response;

	}

	@PostMapping("/addOrder")
	@CheckMethod
	public String addOrder(@ModelAttribute("orderDTO") OrderDTO orderDTO, Model model, HttpServletRequest request) {
		String response = "";
		try {
			UserDTO userDTO = (UserDTO) request.getSession().getAttribute(CommonConstant.LOGIN_USER_SESSION_KEY);
			if (orderDTO == null || !CommonUtil.validString(orderDTO.getName())
					|| !CommonUtil.validBigDecimal(orderDTO.getSinglePrice())
					|| !CommonUtil.validInteger(orderDTO.getCount()) || orderDTO.getReceivedDate() == null
					|| orderDTO.getCategoryDTO() == null || !CommonUtil.validLong(orderDTO.getCategoryDTO().getId())
					|| orderDTO.getSupplierDTO() == null || !CommonUtil.validLong(orderDTO.getSupplierDTO().getId())
					|| !CommonUtil.validLong(orderDTO.getNoteId())) {
				response = "redirect:/note-list.html?addOrder=1";
			} else {
				orderDTO.setUserDTO(userDTO);
				Long orderId = orderService.saveOrder(orderDTO);
				if (CommonUtil.validLong(orderId)) {
					response = "redirect:/note-list.html?addOrder=3";
				} else {
					response = "redirect:/note-list.html?addOrder=4";
				}
			}
			if (CommonUtil.validString(orderDTO.getFilterItemName())) {
				response += "&itemName=" + orderDTO.getFilterItemName();
			}
			if (CommonUtil.validLong(orderDTO.getFilterCategoryId())) {
				response += "&categoryId=" + Long.valueOf(orderDTO.getFilterCategoryId());
			}
			if (CommonUtil.validLong(orderDTO.getFilterNotedUserId())) {
				response += "&notedUserId=" + Long.valueOf(orderDTO.getFilterNotedUserId());
			}
			if (CommonUtil.validLong(orderDTO.getNotedSupplier())) {
				response += "&notedSupplierId=" + Long.valueOf(orderDTO.getNotedSupplier());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = "redirect:/note-list.html?addOrder=4";
		}
		return response;
	}

	@CheckMethod
	public void commonModelList(Model model) {
		List<CategoryDTO> categoryDTOList = categoryService.getALL();
		List<UserDTO> userDTOList = userService.getAllUser(CommonStatus.ACTIVE.getCode());
		model.addAttribute("categoryDTOList", categoryDTOList);
		model.addAttribute("userDTOList", userDTOList);
	}

	public void commonModel(Model model, String pageTitle) {
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("activePage", AdminPages.NOTE.getCode());
	}

	public void showMessage(Model model, MessageType messageType, String message) {
		model.addAttribute("msgType", messageType);
		model.addAttribute("message", message);
		model.addAttribute("showAlertDisplay", "block");
	}
}
