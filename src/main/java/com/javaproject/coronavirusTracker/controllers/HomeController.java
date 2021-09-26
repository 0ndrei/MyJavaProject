package com.javaproject.coronavirusTracker.controllers;

import com.javaproject.coronavirusTracker.services.CoronaVDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CoronaVDataService coronaVDataService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", coronaVDataService.getAllStats());
        return "home";
    }
}
