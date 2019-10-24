package com.rudy.ladl.controller;

import com.rudy.ladl.entity.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class SiteController {

    private static final String template = "Site, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/site")
    public User greeting(@RequestParam(value="name", defaultValue="la croix") String name) {
        return new User();
    }
}