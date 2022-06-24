package com.hrs.controller.frontend;

import com.hrs.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomePageController {
	public static String token = "";

	@Autowired
	TourService tourService;

	@GetMapping(value = { "/homepage"})
	public String viewHomePage(Model model) {
		model.addAttribute("tours", tourService.getAll());
		return "frontend/homepage";
	}

}
