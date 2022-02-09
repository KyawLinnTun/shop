package com.myprojects.admin.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.myprojects.admin.common.CommonConstant;
import com.myprojects.admin.service.order.OrderService;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	OrderService orderService;

	private List<String> ignoreUrls = null;

	{
		ignoreUrls = Arrays.asList("login.html", "logout.html");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();

		String queryString = request.getQueryString();
		if (queryString != null && queryString.contains("language=mm")) {
			request.getSession().setAttribute(CommonConstant.LANGUAGE, CommonConstant.LANGUAGE_MM);
		} else if (queryString != null && queryString.contains("language=en")) {
			request.getSession().setAttribute(CommonConstant.LANGUAGE, "");
		}
		request.getSession().setAttribute(CommonConstant.IMAGEPATH, CommonConstant.IMAGE_PATH);

		try {
			request.getSession().setAttribute(CommonConstant.UPCOMING_SUPPLIERS_COUNT,
					orderService.getUpcomingOrders().size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!isIgnoreUrl(uri)) {
			if (request.getSession().getAttribute(CommonConstant.LOGIN_USER_SESSION_KEY) == null) {
				response.sendRedirect("login.html");
				return false;
			}
		}
		return true;
	}

	private boolean isIgnoreUrl(String url) {
		for (String ignoreUrl : ignoreUrls) {
			if (url.endsWith(ignoreUrl)) {
				return true;
			}
		}
		return false;
	}
}
