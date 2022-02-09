package com.myprojects.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myprojects.admin.aspect.CheckMethod;
import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.common.enumeration.MessageType;
import com.myprojects.admin.common.enumeration.UserType;
import com.myprojects.admin.service.order.OrderService;
import com.myprojects.admin.service.user.UserService;
import com.myprojects.admin.util.user.UserDTO;

@Controller
public class WelcomeController {

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest req) {
		model.addAttribute("userDto", new UserDTO());
		return "login";
	}

	@PostMapping("/login")
	@CheckMethod
	public String checkAuth(@ModelAttribute("userDto") UserDTO userDTO, Model model, HttpServletRequest request) {
		UserDTO loggedInUser = userService.checkAuth(userDTO);
		if (loggedInUser != null) {
			loggedInUser.setPassword("");
			request.getSession().setAttribute(CommonConstant.LOGIN_USER_SESSION_KEY, loggedInUser);
			request.getSession().setAttribute(CommonConstant.ADMIN_KEY, UserType.EMPLOYEER.getCode());
			return "redirect:dashboard.html";
		} else {
			model.addAttribute("errorMsg", "labels.login_fail_msg");
			model.addAttribute("userDto", new UserDTO());
			return "login";
		}
	}

	@RequestMapping(value = "/dashboard.html", method = RequestMethod.GET)
	public String dashboard(Model model, HttpServletRequest req) {
		return "welcome";
	}

	@GetMapping("/logout")
	@CheckMethod
	public String logOut(Model model, HttpServletRequest request) {
		request.getSession().setAttribute(CommonConstant.LOGIN_USER_SESSION_KEY, null);
		request.getSession().setAttribute(CommonConstant.LANGUAGE, null);
		request.getSession().setAttribute(CommonConstant.IMAGEPATH, null);
		request.getSession().setAttribute(CommonConstant.UPCOMING_SUPPLIERS_COUNT, null);
		model.addAttribute("userDTO", new UserDTO());
		return "redirect:login.html";
	}

	public void showMessage(Model model, MessageType messageType, String message) {
		model.addAttribute("msgType", messageType);
		model.addAttribute("message", message);
		model.addAttribute("showAlertDisplay", "block");
	}
}
