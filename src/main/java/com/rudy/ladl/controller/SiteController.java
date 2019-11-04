package com.rudy.ladl.controller;

import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.user.User;
import com.rudy.ladl.service.DepartmentService;
import com.rudy.ladl.service.SiteService;
import com.rudy.ladl.service.UserService;
import com.rudy.ladl.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class SiteController {

    private DepartmentService departmentService;

    public SiteController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private SiteService siteService;
    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping(Constant.SITE_LIST_PATH)
    public String getAllUsers(Model model) {
        model.addAttribute("sites", siteService.findAll());
        return Constant.SITE_LIST_PAGE;
    }

    @GetMapping(Constant.SITE_ADD_PATH)
    public String siteAddForm (Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return Constant.SITE_LIST_PAGE;
    }
}