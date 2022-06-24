package com.hrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ErrorController {

    // for 403 access denied page
    @GetMapping(value = "/error/403")
    public String accesssDenied(Principal principal) {
        return "error_403";
    }

}
