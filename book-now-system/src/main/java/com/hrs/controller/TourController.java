package com.hrs.controller;

import com.hrs.model.dto.LoginDTO;
import com.hrs.model.dto.TourDTO;
import com.hrs.model.reponse.TourResponse;
import com.hrs.service.LocationService;
import com.hrs.service.TourService;
import com.hrs.utils.ConstantUtils;
import com.hrs.utils.DateUtils;
import com.hrs.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/masterdata/tours")
public class TourController {

    @Autowired
    TourService tourService;

    @Autowired
    LocationService locationService;

    @Autowired
    ErrorUtils errorUtils;

    @Autowired
    private DateUtils dateUtils;

    @GetMapping(value = "/")
    public String viewTour(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        List<TourResponse> tourResponses = tourService.getList(token);
        model.addAttribute("HOST_URL", ConstantUtils.HOST_URL);
        model.addAttribute("tours", tourResponses);
        model.addAttribute("errors", new HashMap<>());
        return "tour";
    }

    @GetMapping(value = {"/form"})
    public String create(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        model.addAttribute("tourDTO", new TourDTO());
        model.addAttribute("locations", locationService.getList(token));
        model.addAttribute("errors", new HashMap<>());
        return "tour_form";
    }

    @GetMapping(value = {"/form/{id}"})
    public String edit(Model model, @PathVariable long id, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        TourResponse response = tourService.getById(token, id);
        if (response == null) {
            response = new TourResponse();
        }
        response.setCusTourDateDepart(convertDate2String(response.getTourDateDepart(), "yyyy-MM-dd"));
        response.setCusTourDateReturn(convertDate2String(response.getTourDateReturn(), "yyyy-MM-dd"));
        model.addAttribute("tourDTO", response);
        model.addAttribute("locations", locationService.getList(token));
        model.addAttribute("errors", new HashMap<>());
        return "tour_form";
    }

    @PostMapping(value = "/form/save", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String save(Model model, @Valid TourDTO tourDTO, BindingResult bindingResult, HttpSession session, @RequestPart MultipartFile image) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        Map<String, String> errorList = new HashMap<>();
        boolean checkDateMoreThan = false;
        boolean checkDateCurrent = false;

        Date startDate = dateUtils.convertStringToDate("yyyy-MM-dd", tourDTO.getCusTourDateDepart());
        Date endDate = dateUtils.convertStringToDate("yyyy-MM-dd", tourDTO.getCusTourDateReturn());

        if (!ObjectUtils.isEmpty(startDate) && !ObjectUtils.isEmpty(endDate) && startDate.compareTo(endDate) > 0){
            checkDateMoreThan = true;
        }

        if (!ObjectUtils.isEmpty(startDate) && !ObjectUtils.isEmpty(endDate)
                && startDate.compareTo(dateUtils.truncateTime(new Date())) < 0){
            checkDateCurrent = true;
        }

        if (bindingResult.hasErrors()) {
            errorUtils.loadErrorList(bindingResult, errorList);
        } else {
            if (tourDTO.getName() == null || tourDTO.getName().trim().length() == 0) {
                errorList.put("name", "Vui lòng nhập tên tour!");
            }
            if (ObjectUtils.isEmpty(startDate)) {
                errorList.put("checkinDate", "Vui lòng nhập Ngày Bắt Đầu!");
            }
            if (ObjectUtils.isEmpty(endDate)) {
                errorList.put("checkoutDate", "Vui lòng nhập Ngày Kết Thúc!");
            }
            if (checkDateMoreThan) {
                errorList.put("dateError", "Ngày Bắt Đầu lớn hơn Ngày Kết Thúc!");
            }
            if (checkDateCurrent) {
                errorList.put("dateError", "Ngày Bắt Đầu nhỏ hơn ngày hiện tại!");
            }
        }

        if (errorList.size() > 0) {
            model.addAttribute("errors", errorList);
            model.addAttribute("tourDTO", tourDTO);
            model.addAttribute("locations", locationService.getList(session.getAttribute("token").toString()));
            return "tour_form";
        } else {
            // create or update
            tourDTO.setImage(image);
            tourDTO.setTourDateDepart(tourDTO.getCusTourDateDepart());
            tourDTO.setTourDateReturn(tourDTO.getCusTourDateReturn());
            tourService.save(session.getAttribute("token").toString(), tourDTO);
        }

        model.addAttribute("tourDTO", tourDTO);
        model.addAttribute("locations", locationService.getList(session.getAttribute("token").toString()));
        model.addAttribute("errors", new HashMap<>());

        return "redirect:/masterdata/tours/";
    }

    @GetMapping(value = {"/form/delete/{id}"})
    public String delete(Model model, @PathVariable long id, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            model.addAttribute("loginDTO", new LoginDTO());
            model.addAttribute("error", "error");
            return "login";
        }

        Map<String, String> errorList = new HashMap<>();
        TourDTO tourDTO = new TourDTO();
        tourDTO.setId(id);
        tourDTO.setStatus(false);
        tourService.deleteTour(token, tourDTO);

        return "redirect:/masterdata/tours/";
    }

    private String convertDate2String(Date date, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            String strDate = formatter.format(date);
            return strDate;
        } catch (Exception ex) {
            return "";
        }
    }

}
