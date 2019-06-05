package com.linln.api.user.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    @RequestMapping("/login")
    public String Login(){
        return "1111";
    }
}
