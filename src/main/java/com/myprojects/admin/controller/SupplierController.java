package com.myprojects.admin.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonAjaxResponse;
import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.CommonUtil;
import com.myprojects.admin.common.enumeration.AdminPages;
import com.myprojects.admin.common.enumeration.CommonStatus;
import com.myprojects.admin.common.enumeration.MessageType;
import com.myprojects.admin.service.cardnumber.CardNumberService;
import com.myprojects.admin.service.category.CategoryService;
import com.myprojects.admin.service.paymenttype.PaymentTypeService;
import com.myprojects.admin.service.supplier.SupplierService;
import com.myprojects.admin.service.suppliercategory.SupplierCategoryService;
import com.myprojects.admin.util.cardnumber.CardNumberDTO;
import com.myprojects.admin.util.category.CategoryDTO;
import com.myprojects.admin.util.paymenttype.PaymentTypeDTO;
import com.myprojects.admin.util.supplier.SupplierDTO;

@Controller
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private PaymentTypeService paymentTypeService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SupplierCategoryService scService;
	@Autowired
	private CardNumberService cardNumberService;

	@GetMapping("/supplier")
	@CheckMethod
	public String getSupplier(@RequestParam(name = "id", required = false) Long id, Model model,
			HttpServletRequest req) {
		SupplierDTO supplierDTO = new SupplierDTO();
		if (CommonUtil.validLong(id)) {
			supplierDTO = supplierService.getById(id);
		}
		model.addAttribute("supplierDTO", supplierDTO);
		commonModelList(model, id);
		commonModel(model, "labels.supplier_create_menu");
		return "supplier";
	}

	@PostMapping("/supplier")
	@CheckMethod
	public String postSupplier(@ModelAttribute("supplierDTO") SupplierDTO supplierDTO, Model model,
			HttpServletRequest request) {
		Long supplierId = null;
		try {
			SupplierDTO dto = supplierService.saveOrUpdateSupplier(supplierDTO);
			if (dto == null && CommonUtil.validLong(supplierDTO.getId())) {
				showMessage(model, MessageType.DANGER, "labels.update_fail_msg");
				model.addAttribute("supplierDTO", supplierDTO);
				supplierId = supplierDTO.getId();
			} else if (CommonUtil.validLong(supplierDTO.getId())) {
				showMessage(model, MessageType.SUCCESS, "labels.update_success_msg");
				model.addAttribute("supplierDTO", new SupplierDTO());
			}
			if (dto == null && !CommonUtil.validLong(supplierDTO.getId())) {
				showMessage(model, MessageType.DANGER, "labels.save_fail_msg");
				model.addAttribute("supplierDTO", supplierDTO);
			} else if (!CommonUtil.validLong(supplierDTO.getId())) {
				showMessage(model, MessageType.SUCCESS, "labels.save_success_msg");
				model.addAttribute("supplierDTO", new SupplierDTO());
			}
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.save_fail_msg");
		}
		commonModelList(model, supplierId);
		commonModel(model, "labels.supplier_create_menu");
		return "supplier";
	}

	@GetMapping("/supplier-list")
	@CheckMethod
	public String getSupplierList(Model model, HttpServletRequest request) {
		List<SupplierDTO> supplierList = new ArrayList<SupplierDTO>();
		try {
			supplierList = supplierService.searchSupplier(null,
					(String) request.getSession().getAttribute(CommonConstant.LANGUAGE));

		} catch (Exception e) {
			showMessage(model, MessageType.DANGER, "labels.system_error");
		}
		model.addAttribute("supplierList", supplierList);
		List<CategoryDTO> categoryDTOList = categoryService.getALL();
		model.addAttribute("categoryDTOList", categoryDTOList);
		model.addAttribute("supplierDTO", new SupplierDTO());
		model.addAttribute("statusList", CommonStatus.values());
		commonModel(model, "labels.supplier_list_menu");

		String delSup = request.getParameter("delSup");
		if (CommonUtil.validString(delSup) && delSup.equals("1")) {
			showMessage(model, MessageType.SUCCESS, "labels.delete_success_msg");
		} else if (CommonUtil.validString(delSup) && delSup.equals("2")) {
			showMessage(model, MessageType.DANGER, "labels.delete_fail_msg");
		}
		return "supplier-list";
	}

	@PostMapping("/supplier-list")
	@CheckMethod
	public String postMapping(@ModelAttribute("supplierDTO") SupplierDTO supplierDTO, Model model,
			HttpServletRequest request) {
		List<SupplierDTO> supplierList = new ArrayList<SupplierDTO>();
		try {
			supplierList = supplierService.searchSupplier(supplierDTO,
					(String) request.getSession().getAttribute(CommonConstant.LANGUAGE));
		} catch (Exception e) {
			showMessage(model, MessageType.DANGER, "labels.system_error");
		}
		model.addAttribute("supplierList", supplierList);
		List<CategoryDTO> categoryDTOList = categoryService.getALL();
		model.addAttribute("categoryDTOList", categoryDTOList);
		model.addAttribute("supplierDTO", supplierDTO);
		model.addAttribute("statusList", CommonStatus.values());
		commonModel(model, "labels.supplier_list_menu");
		return "supplier-list";
	}

	@RequestMapping(value = "/addPhoneNumber.html", method = RequestMethod.GET)
	public @ResponseBody String getDuplicateClientForm(Model model,
			@RequestParam(value = "phoneNumbersTemp", required = false) String phoneNumbersTemp,
			@RequestParam(value = "addedPhoneNumbers", required = false) String addedPhoneNumbers,
			@RequestParam(value = "selectedPhoneNumbers") List<String> selectedPhoneNumbers,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		String dtoJson = "";
		boolean valid = true;
		for (String key : selectedPhoneNumbers) {
			if (key.equalsIgnoreCase(addedPhoneNumbers)) {
				resultMap.put("isAlreadyExists", addedPhoneNumbers);
				valid = false;
			}
		}
		List<String> oldList = new ArrayList<String>();
		List<String> resultList = new ArrayList<String>();
		if (CommonUtil.validString(phoneNumbersTemp)) {
			String[] temps = phoneNumbersTemp.trim().split(",");
			for (String k : temps) {
				if (CommonUtil.validString(k.trim())) {
					oldList.add(k);
				}
			}
		}
		if (CommonUtil.validList(selectedPhoneNumbers)) {
			for (String k : oldList) {
				if (selectedPhoneNumbers.contains(k.trim())) {
					resultList.add(k);
				}
			}
		}
		if (valid) {
			if (CommonUtil.validString(addedPhoneNumbers)) {
				resultList.add(addedPhoneNumbers);
			}
		}
		resultMap.put("phoneNumbers", resultList);
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			dtoJson = ow.writeValueAsString(resultMap);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "error";
		}
		return dtoJson;

	}

	@PostMapping("/deleteSupplierById")
	@CheckMethod
	public String deleteDoctorById(HttpServletRequest req, Model model) {
		Long id = Long.valueOf(req.getParameter("id"));
		String response = "";
		if (!CommonUtil.validLong(id)) {
			response = "redirect:/supplier-list.html?delSup=2";
		} else {
			commonModel(model, "labels.supplier_list_menu");
			if (supplierService.deleteSupplierById(id)) {
				response = "redirect:/supplier-list.html?delSup=1";
			} else {
				response = "redirect:/supplier-list.html?delSup=2";
			}

		}
		return response;
	}

	@CheckMethod
	public void commonModelList(Model model, Long supplierId) {
		List<PaymentTypeDTO> paymentTypeDTOList = paymentTypeService.getAll();
		List<CategoryDTO> categoryDTOList = categoryService.getALL();
		if (CommonUtil.validLong(supplierId)) {
			List<CategoryDTO> selectedCategoryList = scService.getCategoryDTOBySupplierId(supplierId);
			if (CommonUtil.validList(selectedCategoryList)) {
				for (CategoryDTO cat : categoryDTOList) {
					for (CategoryDTO c : selectedCategoryList) {
						if (cat.getId().equals(c.getId())) {
							cat.setSelectedFacility(true);
						}
					}
				}
			}
		}
		List<CardNumberDTO> cardNumberDTOList = cardNumberService.getCardNumberBySupplierId(supplierId);
		model.addAttribute("paymentTypeDTOList", paymentTypeDTOList);
		model.addAttribute("categoryDTOList", categoryDTOList);
		model.addAttribute("cardNumberDTOList", cardNumberDTOList);
		model.addAttribute("statusList", CommonStatus.values());
	}

	@ResponseBody
	@RequestMapping(value = "/getPaymentMethod.html", method = RequestMethod.GET)
	public String getDurationServiceByJoiningDate(HttpServletRequest request) {
		CommonAjaxResponse resp = new CommonAjaxResponse();
		Gson g = new Gson();
		CardNumberDTO cardNumberDTO = null;
		if (CommonUtil.validString(request.getParameter("supplierId"))
				&& CommonUtil.validString(request.getParameter("array"))) {
			List<CardNumberDTO> cardNumberDTOList = cardNumberService
					.getCardNumberBySupplierId(Long.valueOf(request.getParameter("supplierId")));
			if (cardNumberDTOList.size() - 1 >= Integer.valueOf(request.getParameter("array"))) {
				cardNumberDTO = cardNumberDTOList.get(Integer.valueOf(request.getParameter("array")));
			}
		}
		if (cardNumberDTO != null && CommonUtil.validLong(cardNumberDTO.getPaymentTypeId())
				&& CommonUtil.validString(cardNumberDTO.getNo())) {
			resp.setData(g.toJson(cardNumberDTO.getPaymentTypeId().toString() + "/" + cardNumberDTO.getNo() + "/"
					+ cardNumberDTO.getName() + "/" + request.getParameter("array")));
		} else {
			resp.setInformation("Fail");
		}
		return g.toJson(resp);
	}

	public void commonModel(Model model, String pageTitle) {
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("activePage", AdminPages.SUPPLIER.getCode());
	}

	public void showMessage(Model model, MessageType messageType, String message) {
		model.addAttribute("msgType", messageType);
		model.addAttribute("message", message);
		model.addAttribute("showAlertDisplay", "block");
	}

	@GetMapping("getSupplierListUrl")
	@CheckMethod
	public @ResponseBody String getSupplierListByCheckingItemUrl(@RequestParam(name = "categoryId") Long categoryId,
			HttpServletRequest req,Model model, HttpServletRequest request) {
		List<SupplierDTO> supplierList = new ArrayList<>();
		CommonAjaxResponse resp = new CommonAjaxResponse();
		try {
			supplierList=supplierService.getSupplierLabelAndIdByCategoryId(categoryId,
					(String) request.getSession().getAttribute(CommonConstant.LANGUAGE));
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccessMessage("labels.system_error");
		}
		Gson g = new Gson();
		resp.setData(g.toJson(supplierList));
		return g.toJson(resp);
	}
}
