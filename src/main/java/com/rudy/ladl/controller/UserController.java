package com.rudy.ladl.controller;

import com.rudy.ladl.controller.form.RegisterForm;
import com.rudy.ladl.entity.user.User;
import com.rudy.ladl.exception.EmailNotAvailableException;
import com.rudy.ladl.exception.UsernameNotAvailableException;
import com.rudy.ladl.service.UserService;
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
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/register")
    public String registerForm(RegisterForm registerForm, BindingResult bindingResult){
        return "register";
    }

    //TODO handle error with a redirection and an error message on register
    @PostMapping("/user/register")
    public String registerSubmit(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult bindingResult) {
        if(!registerForm.getUser().getPassword().equals(registerForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Les mots de passe ne sont pas identiques");
        }
        if (bindingResult.hasErrors()){
            return "register";
        }

        try{
            userService.addUser(registerForm.getUser());
        } catch(EmailNotAvailableException | UsernameNotAvailableException e) {
            if (e instanceof EmailNotAvailableException) {
                bindingResult.rejectValue("user.email", "error.user.email", e.getMessage());
            } else {
                bindingResult.rejectValue("user.username", "error.user.username", e.getMessage());
            }
            return "register";
        }

        return "result";
    }

    //TODO remove
    @GetMapping({"/users", "/user"})
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/login")
    public String loginForm (User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            return "index";
        }
        return "login";
    }
}