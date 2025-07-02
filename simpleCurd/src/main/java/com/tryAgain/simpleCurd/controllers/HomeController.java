package com.tryAgain.simpleCurd.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HomeController {

    @GetMapping()
    public String getHomePage() {
        return "hello Home page";
    }
    @GetMapping("/dashboard")
    public String getdashboard() {
        return "dashboard";
    }
}
