package com.rudy.ladl.controller;

import com.rudy.ladl.core.dto.RegisterDTO;
import com.rudy.ladl.core.dto.SiteContributionDTO;
import com.rudy.ladl.core.dto.TopoReservationDTO;
import com.rudy.ladl.core.site.Comment;
import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.user.Topo;
import com.rudy.ladl.core.user.User;
import com.rudy.ladl.service.DepartmentService;
import com.rudy.ladl.service.TopoService;
import com.rudy.ladl.service.UserService;
import com.rudy.ladl.util.Constant;
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
public class TopoController {

    private TopoService topoService;
    private DepartmentService departmentService;
    private UserService userService;

    public TopoController (TopoService topoService, DepartmentService departmentService, UserService userService) {
        this.topoService = topoService;
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @GetMapping(Constant.TOPOS_PATH)
    public String getAllTopos(Model model) {
        model.addAttribute("topos", topoService.findAll());
        return Constant.TOPO_LIST_PAGE;
    }

    @GetMapping(Constant.USER_TOPO_LIST_PATH)
    public String getAllUserTopos(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("topos", topoService.findAllByOwner(user));
        return Constant.USER_TOPO_LIST_PAGE;
    }

    @GetMapping(Constant.TOPO_ADD_PATH)
    public String topoAddForm(Model model) {
        if (!model.containsAttribute("topo")) {
            model.addAttribute("topo", new Topo());
        }
        model.addAttribute("departments", departmentService.findAll());

        return Constant.TOPO_ADD_PAGE;
    }

    @PostMapping(Constant.TOPO_ADD_PATH)
    public String topoAddSubmit(@Valid @ModelAttribute("Topo") Topo topo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }
        User user = userService.findByUsername(authentication.getName());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.topo", bindingResult);
            redirectAttributes.addFlashAttribute("topo", topo);
            return Constant.REDIRECT + Constant.TOPO_ADD_PATH;
        }

        topo = topoService.addTopo(topo, user);

        return Constant.REDIRECT + Constant.TOPOS_PATH + Constant.SLASH + topo.getId();
    }

    @GetMapping(Constant.TOPOS_PATH + Constant.SLASHID_PATH)
    public String goToTopoDetailById(@PathVariable("id") Long id, Model model) {
        if(!model.containsAttribute("topoReservationDTO")) model.addAttribute("topoReservationDTO", new TopoReservationDTO());
        addCurrentUserToModel(model);

        Topo topo = topoService.findById(id);
        if (topo != null) {
            model.addAttribute("topo", topo);
            return Constant.TOPO_DETAILS_PAGE;
        }
        return Constant.REDIRECT + Constant.TOPOS_PATH;
    }

    @PostMapping(Constant.TOPO_ADD_RESERVATION_PATH)
    public String addReservation(Topo topo, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }

        User user = userService.findByUsername(authentication.getName());
        topo = topoService.findById(topo.getId());

        topoService.addReservation(topo, user);

        return Constant.REDIRECT + Constant.TOPOS_PATH + Constant.SLASH + topo.getId();
    }

    @GetMapping(Constant.USER_TOPO_RESERVATIONS_LIST_PATH)
    public String getAllUserReservation(Model model) {
        if(!model.containsAttribute("topoReservationDTO")) model.addAttribute("topoReservationDTO", new TopoReservationDTO());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }
        User user = userService.findByUsername(authentication.getName());

        model.addAttribute("currentUser", user);
        model.addAttribute("topos", topoService.findAllReservationsFromUser(user));
        model.addAttribute("toposAskers", topoService.findAllReservationsToUser(user));
        model.addAttribute("toposLent", topoService.findAllLendToUser(user));
        model.addAttribute("toposBorrowed", topoService.findAllLendFromUser(user));
        return Constant.USER_TOPO_RESERVATIONS_LIST_PAGE;
    }

    @PostMapping(Constant.TOPO_REMOVE_RESERVATION_PATH)
    public String removeUserReservation(TopoReservationDTO topoReservationDTO, Model model) {
        if (topoReservationDTO.getTopo().getId() == null || topoReservationDTO.getUser().getId() == null) {
            return Constant.REDIRECT + Constant.USER_TOPO_RESERVATIONS_LIST_PATH;
        }
        Topo topo = topoService.findById(topoReservationDTO.getTopo().getId());
        User user = userService.findById(topoReservationDTO.getUser().getId());
        if(topo == null || user == null) {
            return Constant.REDIRECT + Constant.USER_TOPO_RESERVATIONS_LIST_PATH;
        }

        topoService.removeReservationFromUser(topo, user);

        return Constant.REDIRECT + Constant.USER_TOPO_RESERVATIONS_LIST_PATH;
    }

    @PostMapping(Constant.TOPO_ACCEPT_RESERVATION_PATH)
    public String acceptUserReservation(TopoReservationDTO topoReservationDTO, Model model) {
        if (topoReservationDTO.getTopo().getId() == null || topoReservationDTO.getUser().getId() == null) {
            return Constant.REDIRECT + Constant.USER_TOPO_RESERVATIONS_LIST_PATH;
        }
        Topo topo = topoService.findById(topoReservationDTO.getTopo().getId());
        User user = userService.findById(topoReservationDTO.getUser().getId());
        if(topo == null || user == null) {
            return Constant.REDIRECT + Constant.USER_TOPO_RESERVATIONS_LIST_PATH;
        }

        topoService.acceptReservationFromUser(topo, user);

        return Constant.REDIRECT + Constant.USER_TOPO_RESERVATIONS_LIST_PATH;
    }

    @PostMapping(Constant.TOPO_SET_AVAILABLE_PATH)
    public String setTopoAvailable(Topo topo, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }

        topo = topoService.findById(topo.getId());
        User user = userService.findByUsername(authentication.getName());
        if(topo == null || user == null) {
            return Constant.REDIRECT + Constant.USER_TOPO_LIST_PATH;
        }

        topoService.setTopoAvailable(topo, user);

        return Constant.REDIRECT + Constant.TOPOS_PATH + Constant.SLASH + topo.getId();
    }

    @PostMapping(Constant.TOPO_SET_NOT_AVAILABLE_PATH)
    public String setTopoNotAvailable(Topo topo, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            return Constant.REDIRECT + Constant.LOGIN_PATH;
        }

        topo = topoService.findById(topo.getId());
        User user = userService.findByUsername(authentication.getName());
        if(topo == null || user == null) {
            return Constant.REDIRECT + Constant.USER_TOPO_LIST_PATH;
        }

        topoService.setTopoNotAvailable(topo, user);

        return Constant.REDIRECT + Constant.TOPOS_PATH + Constant.SLASH + topo.getId();
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