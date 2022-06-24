package com.hrs.controller;

import com.hrs.model.dto.BookingReportDTO;
import com.hrs.model.dto.LoginDTO;
import com.hrs.model.reponse.BookingResponse;
import com.hrs.service.BookingService;
import com.hrs.service.LocationService;
import com.hrs.utils.DateUtils;
import com.hrs.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reports/booking")
public class ReportBookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ErrorUtils errorUtils;

	@Autowired
	private DateUtils dateUtils;

	@Autowired
	private LocationService locationService;

	@GetMapping(value = {"", "/"})
	public String viewReportBooking(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		if (token == null) {
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("error", "error");
			return "login";
		}

		BookingReportDTO bookingReportDTO = new BookingReportDTO();
		bookingReportDTO.setStartDate(dateUtils.convertDateToString("yyyy-MM-dd", new Date()));
		bookingReportDTO.setEndDate(dateUtils.convertDateToString("yyyy-MM-dd", new Date()));
		List<BookingResponse> bookingResponse = bookingService.getList(session.getAttribute("token").toString());
		model.addAttribute("bookings", bookingResponse);
		model.addAttribute("bookingReportDTO", bookingReportDTO);
		model.addAttribute("errors", new HashMap<>());
		return "report_booking";
	}

	@GetMapping(value = {"/view"})
	public String viewReportBooking(Model model, BookingReportDTO bookingReportDTO, HttpSession session) {
		String token = (String) session.getAttribute("token");
		if (token == null) {
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("error", "error");
			return "login";
		}

		Map<String, String> errorList = new HashMap<>();

		if (bookingReportDTO == null || bookingReportDTO.getEndDate().isEmpty()) {
			errorUtils.addError(errorList, "createDate", "Field 'Create Date' can't be empty!");
		}

		if (bookingReportDTO == null || bookingReportDTO.getEndDate().isEmpty()) {
			errorUtils.addError(errorList, "endDate", "Field 'End Date' can't be empty!");
		}

		List<BookingResponse> bookingResponse = bookingService.getReportBooking(session.getAttribute("token").toString(), bookingReportDTO);
		model.addAttribute("bookings", bookingResponse);
		model.addAttribute("bookingReportDTO", bookingReportDTO);
		model.addAttribute("errors", new HashMap<>());

		return "report_booking";
	}

	@GetMapping(value = {"/view/{id}"})
	public String reportBooking(Model model, @PathVariable long id, HttpSession session) {
		String token = (String) session.getAttribute("token");
		if (token == null) {
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("error", "error");
			return "login";
		}

		BookingResponse bookingResponse = bookingService.getById(token, id);
		if (bookingResponse != null) {
			model.addAttribute("locations", locationService.getList(token));
			model.addAttribute("bookingResponse", bookingResponse);
			return "report_booking_detail";
		}

		String redirectUrl = "/reports/booking/";
		return "redirect:" + redirectUrl;
	}

}
