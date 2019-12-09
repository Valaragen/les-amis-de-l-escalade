package com.rudy.ladl.controller;

import com.rudy.ladl.core.dto.RegisterDTO;
import com.rudy.ladl.core.user.User;
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
        if(!registerDTO.getUser().getPassword().equals(registerDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", Constant.ERROR_MSG_PASSWORD_MISMATCH);
        }
        if (userService.isEmailNotAvailable(registerDTO.getUser().getEmail())) {
            bindingResult.rejectValue("user.email", "error.user.email", Constant.ERROR_MSG_EMAIL_NOT_AVAILABLE);
        }
        if (userService.isUsernameAvailable(registerDTO.getUser().getUsername())) {
            bindingResult.rejectValue("user.username", "error.user.username", Constant.ERROR_MSG_USERNAME_NOT_AVAILABLE);
        }
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            return Constant.REDIRECT + Constant.REGISTRATION_PATH;
        }

        try{
            userService.addUser(registerDTO.getUser());
        } catch(EmailNotAvailableException | UsernameNotAvailableException e) {
            redirectAttributes.addFlashAttribute("message", "Email or username not valid");
            return Constant.REDIRECT + Constant.REGISTRATION_PATH;
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "unhandled error");
            return Constant.REDIRECT + Constant.REGISTRATION_PATH;
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