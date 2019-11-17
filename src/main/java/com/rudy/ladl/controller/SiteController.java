package com.rudy.ladl.controller;

import com.rudy.ladl.entity.site.Department;
import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.service.DepartmentService;
import com.rudy.ladl.service.SiteService;
import com.rudy.ladl.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SiteController {

    private SiteService siteService;
    private DepartmentService departmentService;

    @Autowired
    public SiteController(SiteService siteService, DepartmentService departmentService) {
        this.departmentService = departmentService;
        this.siteService = siteService;
    }

    @GetMapping(Constant.SITES_PATH)
    public String getAllUsers(Model model) {
        model.addAttribute("sites", siteService.findAll());
        return Constant.SITE_LIST_PAGE;
    }

    @GetMapping(Constant.SITE_ADD_PATH)
    public String siteAddForm(Site site, Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return Constant.SITE_ADD_PAGE;
    }

    @PostMapping(Constant.SITE_ADD_PATH)
    public String siteAddSubmit(@Valid @ModelAttribute("Site") Site site, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return Constant.REGISTRATION_PAGE;
        }
        //Add service

        return Constant.REDIRECT + Constant.SITE_LIST_PAGE;
    }

    @GetMapping(Constant.SITES_PATH + Constant.SLASHSTRING_PATH)
    public String goToUserDetailByUsername(@PathVariable("string") String name, Model model) {
        Site site = siteService.findByName(name);
        if (site != null) {
            model.addAttribute("site", site);
            return Constant.SITE_DETAILS_PAGE;
        }
        return Constant.REDIRECT + Constant.SITES_PATH;
    }


}