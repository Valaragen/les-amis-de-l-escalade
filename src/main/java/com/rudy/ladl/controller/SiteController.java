package com.rudy.ladl.controller;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.user.User;
import com.rudy.ladl.service.SiteService;
import com.rudy.ladl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class SiteController {

    private SiteService siteService;
    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping("/site")
    public String greeting(Model model) {
        model.addAttribute("sites", siteService.findAll());
        return "site";
    }
}