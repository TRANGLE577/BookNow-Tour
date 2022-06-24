package com.hrs.controller.frontend;

import com.hrs.model.reponse.TourResponse;
import com.hrs.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DetailController {
    public static String token = "";
    @Autowired
    TourService tourService;
    @GetMapping(value = { "/detail/{id}"})
    public String viewHomePage(Model model, @PathVariable long id) {
        TourResponse tourDetail = tourService.getById(id);
        model.addAttribute("tour", tourDetail);
        model.addAttribute("tours", tourService.getAll());
        return "frontend/detail";
    }
}

