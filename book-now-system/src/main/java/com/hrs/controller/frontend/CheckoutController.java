package com.hrs.controller.frontend;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {
    public static String token = "";

    @GetMapping(value = { "/checkout"})
    public String viewHomePage(Model model) {
        return "frontend/checkout";
    }

}
