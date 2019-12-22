package com.rudy.ladl.controller;

import com.rudy.ladl.core.SiteSearch;
import com.rudy.ladl.core.dto.SiteContributionDTO;
import com.rudy.ladl.core.site.Comment;
import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.site.embeddable.CommentId;
import com.rudy.ladl.core.user.User;
import com.rudy.ladl.exception.SiteFieldAlreadyFilledException;
import com.rudy.ladl.service.*;
import com.rudy.ladl.util.Constant;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.Collection;

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
    private TagService tagService;
    private CommentService commentService;

    @Autowired
    public SiteController(SiteService siteService, DepartmentService departmentService, UserService userService,
                          GradeService gradeService, SiteTypeService siteTypeService, OrientationService orientationService,
                          LevelGroupService levelGroupService, RockTypeService rockTypeService, RoutesNumberService routesNumberService,
                          TagService tagService, CommentService commentService) {
        this.departmentService = departmentService;
        this.siteService = siteService;
        this.userService = userService;
        this.gradeService = gradeService;
        this.siteTypeService = siteTypeService;
        this.orientationService = orientationService;
        this.levelGroupService = levelGroupService;
        this.rockTypeService = rockTypeService;
        this.routesNumberService = routesNumberService;
        this.tagService = tagService;
        this.commentService = commentService;
    }

    @GetMapping(Constant.SITES_PATH)
    public String getAllSites(@ModelAttribute("siteSearch") SiteSearch siteSearch, Model model) {
        if(!model.containsAttribute("siteSearch")) {
            model.addAttribute("siteSearch", new SiteSearch());
        }

        model.addAttribute("sites", siteService.search(siteSearch));

        loadEntityModelAttribute(model);
        return Constant.SITE_LIST_PAGE;
    }

    @GetMapping(Constant.SITE_ADD_PATH)
    public String siteAddForm(Model model) {
        if (!model.containsAttribute("site")) {
            model.addAttribute("site", new Site());
        }
        loadEntityModelAttribute(model);
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
    public String goToSiteDetailByName(@PathVariable("string") String name, SiteContributionDTO siteContributionDTO, Comment comment, Model model) {
        addCurrentUserToModel(model);
        Site site = siteService.findByName(name);
        loadEntityModelAttribute(model);
        if (site != null) {
            model.addAttribute("site", site);
            return Constant.SITE_DETAILS_PAGE;
        }
        return Constant.REDIRECT + Constant.SITES_PATH;
    }

    @PostMapping(Constant.SITES_PATH + Constant.SLASHSTRING_PATH)
    public String addSiteDetails(@PathVariable("string") String name, @ModelAttribute("SiteContributionDTO") SiteContributionDTO siteContributionDTO, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }
        User user = userService.findByUsername(authentication.getName());
        Site site = siteService.findByName(name);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.site", bindingResult);
            redirectAttributes.addFlashAttribute("siteContributionDTO", siteContributionDTO);
            return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + name;
        }

        try {
            siteService.addContribution(site, siteContributionDTO, user);
        } catch (SiteFieldAlreadyFilledException e) {
            return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + name;
        }

        return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + name;
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping(Constant.SITE_ADD_OFFICIAL_TAG_PATH)
    public String addOfficialTag(@ModelAttribute("Site") Site site, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }

        User user = userService.findByUsername(authentication.getName());
        site = siteService.findByName(site.getName());
        tagService.addOfficialTagOnSite(site, user);

        return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + site.getSearchName();
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping(Constant.SITE_REMOVE_OFFICIAL_TAG_PATH)
    public String removeOfficialTag(@ModelAttribute("Site") Site site, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }

        site = siteService.findByName(site.getName());
        tagService.removeOfficialTagOnSite(site);

        return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + site.getSearchName();
    }

    @PostMapping(Constant.SITE_ADD_COMMENT_PATH + Constant.SLASHSTRING_PATH)
    public String addComment(@PathVariable("string") String name, @ModelAttribute("Comment") Comment comment, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }

        User user = userService.findByUsername(authentication.getName());
        Site site = siteService.findByName(name);
        commentService.addComment(comment, site, user);

        return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + site.getSearchName();
    }

    @PostMapping(Constant.SITE_MODIFY_COMMENT_PATH )
    public String modifyComment(@ModelAttribute("comment") Comment comment, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        User user = userService.findByUsername(authentication.getName());

        User commentOwner = userService.findById(comment.getId().getUser().getId());
        Site site = siteService.findById(comment.getId().getSite().getId());
        System.out.println(comment.getId().getDate());
        Comment newComment = commentService.findById(new CommentId(site, commentOwner, comment.getId().getDate()));
        newComment.setDescription(comment.getDescription());

        commentService.modifyComment(newComment, user, authorities.contains(new SimpleGrantedAuthority("ROLE_MEMBER")));

        return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + site.getSearchName();
    }

    @PostMapping(Constant.SITE_DELETE_COMMENT_PATH )
    public String deleteComment(@ModelAttribute("comment") Comment comment, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        User user = userService.findByUsername(authentication.getName());

        User commentOwner = userService.findById(comment.getId().getUser().getId());
        Site site = siteService.findById(comment.getId().getSite().getId());
        comment = commentService.findById(new CommentId(site, commentOwner, comment.getId().getDate()));

        commentService.deleteComment(comment, user, authorities.contains(new SimpleGrantedAuthority("ROLE_MEMBER")));

        return Constant.REDIRECT + Constant.SITES_PATH + Constant.SLASH + site.getSearchName();
    }


    private void loadEntityModelAttribute(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("grades", gradeService.findAll());
        model.addAttribute("siteTypes", siteTypeService.findAll());
        model.addAttribute("orientations", orientationService.findAll());
        model.addAttribute("levelGroups", levelGroupService.findAll());
        model.addAttribute("rockTypes", rockTypeService.findAll());
        model.addAttribute("routesNumbers", routesNumberService.findAll());
    }

    private void addCurrentUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());

        if(user != null) {
            model.addAttribute("currentUser", user);
        } else {
            model.addAttribute("currentUser", new User());
        }
    }

}