package com.hrs.controller;

import com.hrs.model.dto.BookingDTO;
import com.hrs.model.dto.LoginDTO;
import com.hrs.model.reponse.BookingResponse;
import com.hrs.service.BookingService;
import com.hrs.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/masterdata/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/")
    public String viewBooking(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        List<BookingResponse> bookingResponse = bookingService.getList(token);
        model.addAttribute("bookings", bookingResponse);
        model.addAttribute("errors", new HashMap<>());
        return "booking";
    }

    @GetMapping(value = {"/form/{id}"})
    public String details(Model model, @PathVariable long id, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        BookingResponse response = bookingService.getById(token, id);
        if (response == null) {
            response = new BookingResponse();
        }
        model.addAttribute("bookingDTO", response);
        model.addAttribute("locations", locationService.getList(session.getAttribute("token").toString()));
        model.addAttribute("errors", new HashMap<>());
        return "booking_form";
    }

    @GetMapping(value = {"/approve/{id}"})
    public String approve(Model model, @PathVariable long id, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        return approveAndReject(model, id, session, 1);
    }

    @GetMapping(value = {"/reject/{id}"})
    public String reject(Model model, @PathVariable long id, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        return approveAndReject(model, id, session, -1);
    }

    @GetMapping(value = {"/getReport/"})
    public String getReport(Model model, @PathVariable long id, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }
        return "report";
    }
    
    private String approveAndReject(Model model, long id, HttpSession session, int progress) {
        BookingResponse response = bookingService.getById(session.getAttribute("token").toString(), id);
        if (response != null) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(response.getId());
            bookingDTO.setAccountId(response.getAccountId());
            bookingDTO.setTourId(response.getTourId());
            bookingDTO.setPaymentId(response.getPaymentId());
            bookingDTO.setStatus(progress);
            bookingDTO.setQuantityChildren(response.getQuantityChildren());
            bookingDTO.setQuantityAdult(response.getQuantityAdult());
            bookingService.create(session.getAttribute("token").toString(), bookingDTO);
        }

        List<BookingResponse> bookingResponse = bookingService.getList(session.getAttribute("token").toString());
        model.addAttribute("bookings", bookingResponse);
        model.addAttribute("errors", new HashMap<>());
        return "booking";
    }

}
