package com.rudy.ladl.controller;

import com.rudy.ladl.entity.site.RoutesNumber;
import com.rudy.ladl.entity.site.Site;
import com.rudy.ladl.entity.user.User;
import com.rudy.ladl.service.*;
import com.rudy.ladl.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SiteController {

    private SiteService siteService;
    private DepartmentService departmentService;
    private UserService userService;
    private GradeService gradeService;
    private SiteTypeService siteTypeService;
    private OrientationService orientationService;
    private LevelGroupService levelGroupService;
    private RockTypeService rockTypeService;
    private RoutesNumberService routesNumberService;

    @Autowired
    public SiteController(SiteService siteService, DepartmentService departmentService, UserService userService,
                          GradeService gradeService, SiteTypeService siteTypeService, OrientationService orientationService,
                          LevelGroupService levelGroupService, RockTypeService rockTypeService, RoutesNumberService routesNumberService) {
        this.departmentService = departmentService;
        this.siteService = siteService;
        this.userService = userService;
        this.gradeService = gradeService;
        this.siteTypeService = siteTypeService;
        this.orientationService = orientationService;
        this.levelGroupService = levelGroupService;
        this.rockTypeService = rockTypeService;
        this.routesNumberService = routesNumberService;
    }

    @GetMapping(Constant.SITES_PATH)
    public String getAllSites(Model model) {
        model.addAttribute("sites", siteService.findAll());
        return Constant.SITE_LIST_PAGE;
    }

    @GetMapping(Constant.SITE_ADD_PATH)
    public String siteAddForm(Model model) {
        if (!model.containsAttribute("site")) {
            model.addAttribute("site", new Site());
        }
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("grades", gradeService.findAll());
        model.addAttribute("siteTypes", siteTypeService.findAll());
        model.addAttribute("orientations", orientationService.findAll());
        model.addAttribute("levelGroups", levelGroupService.findAll());
        model.addAttribute("rockTypes", rockTypeService.findAll());
        model.addAttribute("routesNumbers", routesNumberService.findAll());
        return Constant.SITE_ADD_PAGE;
    }

    @PostMapping(Constant.SITE_ADD_PATH)
    public String siteAddSubmit(@Valid @ModelAttribute("Site") Site site, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }
        User user = userService.findByUsername(authentication.getName());


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.site", bindingResult);
            redirectAttributes.addFlashAttribute("site", site);
            return Constant.REDIRECT + Constant.SITE_ADD_PATH;
        }

        site = siteService.addSite(site, user);

        return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + site.getSearchName();
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