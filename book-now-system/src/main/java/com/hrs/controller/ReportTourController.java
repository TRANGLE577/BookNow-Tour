package com.hrs.controller;

import com.hrs.model.dto.BookingReportDTO;
import com.hrs.model.dto.LoginDTO;
import com.hrs.model.dto.TourReportDTO;
import com.hrs.model.reponse.BookingResponse;
import com.hrs.model.reponse.TourResponse;
import com.hrs.service.BookingService;
import com.hrs.service.LocationService;
import com.hrs.service.TourService;
import com.hrs.utils.ConstantUtils;
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
@RequestMapping("/reports/tour")
public class ReportTourController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ErrorUtils errorUtils;

	@Autowired
	private DateUtils dateUtils;

	@Autowired
	private LocationService locationService;

	@Autowired
	TourService tourService;

	@GetMapping(value = {"", "/"})
	public String viewReportTour(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		if (token == null) {
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("error", "error");
			return "login";
		}

		TourReportDTO tourReportDTO = new TourReportDTO();
		tourReportDTO.setStartDate(dateUtils.convertDateToString("yyyy-MM-dd", new Date()));
		tourReportDTO.setEndDate(dateUtils.convertDateToString("yyyy-MM-dd", new Date()));
		List<TourResponse> tourResponses = tourService.getList(token);
		model.addAttribute("tourReportDTO", tourReportDTO);
		model.addAttribute("HOST_URL", ConstantUtils.HOST_URL);
		model.addAttribute("tours", tourResponses);
		model.addAttribute("errors", new HashMap<>());
		return "report_tour";
	}

	@GetMapping(value = {"/view"})
	public String viewReportTour(Model model, TourReportDTO tourReportDTO, HttpSession session) {
		Map<String, String> errorList = new HashMap<>();
		String token = (String) session.getAttribute("token");
		if (token == null) {
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("error", "error");
			return "login";
		}

		if (tourReportDTO == null || tourReportDTO.getEndDate().isEmpty()) {
			errorUtils.addError(errorList, "createDate", "Field 'Create Date' can't be empty!");
		}

		if (tourReportDTO == null || tourReportDTO.getEndDate().isEmpty()) {
			errorUtils.addError(errorList, "endDate", "Field 'End Date' can't be empty!");
		}

		List<TourResponse> tours = tourService.getReportTour(token, tourReportDTO);
		model.addAttribute("tours", tours);
		model.addAttribute("HOST_URL", ConstantUtils.HOST_URL);
		model.addAttribute("tourReportDTO", tourReportDTO);
		model.addAttribute("errors", new HashMap<>());

		return "report_tour";
	}

	@GetMapping(value = {"/view/{id}"})
	public String reportTour(Model model, @PathVariable long id, HttpSession session) {
		String token = (String) session.getAttribute("token");
		if (token == null) {
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("error", "error");
			return "login";
		}

		TourResponse tourResponse = tourService.getById(token, id);
		if (tourResponse != null) {
			BookingReportDTO bookingReportDTO = new BookingReportDTO();
			bookingReportDTO.setTourId(tourResponse.getId());
			List<BookingResponse> bookings = bookingService.getAllBookingByTour(token, bookingReportDTO);
			model.addAttribute("bookings", bookings);
			model.addAttribute("tourDTO", tourResponse);
			model.addAttribute("locations", locationService.getList(token));
			return "report_tour_detail";
		}

		String redirectUrl = "/reports/tour/";
		return "redirect:" + redirectUrl;
	}

}
