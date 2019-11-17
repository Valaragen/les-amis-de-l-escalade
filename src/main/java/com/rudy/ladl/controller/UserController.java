package com.rudy.ladl.controller;

import com.rudy.ladl.controller.dto.RegisterDTO;
import com.rudy.ladl.entity.user.User;
import com.rudy.ladl.exception.EmailNotAvailableException;
import com.rudy.ladl.exception.UsernameNotAvailableException;
import com.rudy.ladl.service.UserService;
import com.rudy.ladl.util.Constant;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(Constant.REGISTRATION_PATH)
    public String registerForm(Model model){
        // Let's see what Spring MVC has put inside the Model already
        System.out.println(model.asMap());
        if(!model.containsAttribute("registerDTO")) model.addAttribute("registerDTO", new RegisterDTO());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            return Constant.REDIRECT + Constant.HOME_PATH;
        }
        return Constant.REGISTRATION_PAGE;
    }

    //TODO add green validation on correct fields and automatic Ajax check for email and username disponibility
    @PostMapping(Constant.REGISTRATION_PATH)
    public String registerSubmit(@Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        // Let's see what Spring MVC has put inside the Model already
        System.out.println(model.asMap());
        System.out.println("stop");
        if(!registerDTO.getUser().getPassword().equals(registerDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", Constant.ERROR_MSG_PASSWORD_MISMATCH);
        }
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            return Constant.REDIRECT + Constant.REGISTRATION_PATH;
        }

        try{
            userService.addUser(registerDTO.getUser());
        } catch(EmailNotAvailableException | UsernameNotAvailableException e) {
            if (e instanceof EmailNotAvailableException) {
                bindingResult.rejectValue("user.email", "error.user.email", e.getMessage());
            } else {
                bindingResult.rejectValue("user.username", "error.user.username", e.getMessage());
            }
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
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

    @GetMapping(Constant.USERS_PATH + Constant.SLASHSTRING_PATH)
    public String goToUserDetailByUsername(@PathVariable("string") String username, Model model) {
        User user = userService.findByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
            return Constant.USER_DETAILS_PAGE;
        }
        return Constant.REDIRECT + Constant.USERS_PATH;
    }

}