package com.myprojects.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myprojects.admin.common.enumeration.AdminPages;
import com.myprojects.admin.common.enumeration.MessageType;
import com.myprojects.admin.service.order.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping(name = "/upcoming-orders")
	public String login(Model model, HttpServletRequest req) {
		try {
			model.addAttribute("upcomingOrders", orderService.getUpcomingOrders());
		} catch (Exception e) {
			e.printStackTrace();
			showMessage(model, MessageType.DANGER, "labels.system_error");
		}
		model.addAttribute("pageTitle", "labels.upcoming_suppliers_today");
		return "upcoming-orders";
	}

	public void commonModel(Model model, String pageTitle) {
		model.addAttribute("pageTitle", pageTitle);
		model.addAttribute("activePage", AdminPages.ORDER.getCode());
	}

	public void showMessage(Model model, MessageType messageType, String message) {
		model.addAttribute("msgType", messageType);
		model.addAttribute("message", message);
		model.addAttribute("showAlertDisplay", "block");
	}
}
