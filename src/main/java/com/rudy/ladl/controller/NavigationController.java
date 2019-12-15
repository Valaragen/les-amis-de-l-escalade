package com.rudy.ladl.controller;

import com.rudy.ladl.core.SiteSearch;
import com.rudy.ladl.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class NavigationController {

    private String appMode;

    @Autowired
    public NavigationController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("datetime", new Date());
        if(!model.containsAttribute("siteSearch")) {
            model.addAttribute("siteSearch", new SiteSearch());
        }
        model.addAttribute("mode", appMode);
        return Constant.HOME_PAGE;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
