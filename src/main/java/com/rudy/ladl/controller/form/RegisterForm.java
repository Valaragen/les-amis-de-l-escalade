package com.rudy.ladl.controller.form;

import com.rudy.ladl.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterForm {
    @Valid
    private User user;
    @NotBlank
    private String confirmPassword;
}
