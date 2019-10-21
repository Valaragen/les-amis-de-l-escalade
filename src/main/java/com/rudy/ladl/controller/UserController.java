package com.rudy.ladl.controller;

import com.rudy.ladl.core.user.User;
import com.rudy.ladl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/register")
    public String registerForm(User user){
        return "register";
    }

    //TODO handle error with a redirection and an error message on register
    @PostMapping("/user/register")
    public String registerSubmit(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "register";
        }
        return "result";
    }

    //TODO remove
    @GetMapping({"/users", "/user"})
    public String getAllUsers(Model model) {
        List<User> vUsers = userService.findAll();
        model.addAttribute("users", vUsers);
        return "users";
    }
}