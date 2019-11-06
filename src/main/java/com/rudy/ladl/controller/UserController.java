package com.rudy.ladl.controller;

import com.rudy.ladl.controller.dto.RegisterDTO;
import com.rudy.ladl.entity.user.User;
import com.rudy.ladl.exception.EmailNotAvailableException;
import com.rudy.ladl.exception.UsernameNotAvailableException;
import com.rudy.ladl.service.UserService;
import com.rudy.ladl.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(Constant.REGISTRATION_PATH)
    public String registerForm(RegisterDTO registerDTO, BindingResult bindingResult){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            return Constant.REDIRECT + Constant.HOME_PATH;
        }
        return Constant.REGISTRATION_PAGE;
    }

    //TODO add green validation on correct fields and automatic Ajax check for email and username disponibility
    @PostMapping(Constant.REGISTRATION_PATH)
    public String registerSubmit(@Valid @ModelAttribute("registerForm") RegisterDTO registerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(!registerDTO.getUser().getPassword().equals(registerDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", Constant.ERROR_MSG_PASSWORD_MISMATCH);
        }
        if (bindingResult.hasErrors()){
            return Constant.REGISTRATION_PAGE;
        }

        try{
            userService.addUser(registerDTO.getUser());
        } catch(EmailNotAvailableException | UsernameNotAvailableException e) {
            if (e instanceof EmailNotAvailableException) {
                bindingResult.rejectValue("user.email", "error.user.email", e.getMessage());
            } else {
                bindingResult.rejectValue("user.username", "error.user.username", e.getMessage());
            }
            return Constant.REGISTRATION_PAGE;
        }
        redirectAttributes.addAttribute("register", "true");
        return Constant.REDIRECT + Constant.LOGIN_PATH;
    }

    @GetMapping(Constant.USERS_PATH)
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return Constant.USER_LIST_PAGE;
    }

    @GetMapping(Constant.LOGIN_PATH)
    public String loginForm(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            return Constant.REDIRECT + Constant.HOME_PATH;
        }
        return Constant.LOGIN_PAGE;
    }

    @GetMapping(Constant.USERS_PATH + Constant.SLASHUSERNAME_PATH)
    public String goToUserDetailByUsername(@PathVariable("username") String username, Model model) {
        User user = userService.findByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
            return Constant.USER_DETAIL_PAGE;
        }
        return Constant.REDIRECT + Constant.USERS_PATH;
    }

//    @GetMapping("/logout")
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//            return "redirect:/login?logout";
//        }
//        return "redirect:/";
//    }

}