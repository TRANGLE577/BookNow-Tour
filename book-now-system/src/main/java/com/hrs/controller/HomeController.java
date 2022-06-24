package com.hrs.controller;

import com.hrs.model.dto.DashboardDTO;
import com.hrs.model.dto.LoginDTO;
import com.hrs.model.reponse.BookingResponse;
import com.hrs.model.reponse.UserResponse;
import com.hrs.service.AccountService;
import com.hrs.service.BookingService;
import com.hrs.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private AccountService accountService;

	@GetMapping(value = { "/", "/home", "/index", "/dashboard" })
	public String viewHomePage(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		if (token == null) {
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("error", "error");
			return "login";
		}

		DashboardDTO dashboard = dashboardService.showDashboard(session);
		List<BookingResponse> bookingResponse = bookingService.getListInProgress(token);
		model.addAttribute("dashboardDTO", dashboard);
		model.addAttribute("bookings", bookingResponse);
		return "index";
	}


	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		model.addAttribute("error", null);
		return "login";
	}

	@PostMapping("/login")
	public String viewLoginPage(LoginDTO loginDTO, Model model, HttpSession session) {
		UserResponse userResponse = accountService.login(loginDTO);
		if (userResponse != null && userResponse.getRole() != null && userResponse.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
			String token = userResponse.getAccessToken();
			session.setAttribute("token", token);
			session.setAttribute("username", userResponse.getUsername());
			DashboardDTO dashboard = dashboardService.showDashboard(session);
			model.addAttribute("dashboardDTO", dashboard);
			List<BookingResponse> bookingResponse = bookingService.getListInProgress(token);
			model.addAttribute("bookings", bookingResponse);
			return "index";
		}

		model.addAttribute("loginDTO", new LoginDTO());
		model.addAttribute("error", "error");
		return "login";
	}

	@GetMapping("/logout")
	public String viewLogoutPage(Model model, HttpSession session) {
		session.removeAttribute("token");
		session.removeAttribute("username");
		session.invalidate();
		model.addAttribute("loginDTO", new LoginDTO());
		model.addAttribute("error", null);
		return "login";
	}

}
