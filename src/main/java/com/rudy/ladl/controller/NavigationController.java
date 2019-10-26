package com.rudy.ladl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class NavigationController {

    private String appMode;

    @Autowired
    public NavigationController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }
    @GetMapping({"/","/index"})
    public String index(Model model) {
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Valaragen");
        model.addAttribute("mode", appMode);
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
