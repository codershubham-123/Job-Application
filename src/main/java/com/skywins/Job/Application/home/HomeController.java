package com.skywins.Job.Application.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String home(){
        return "Welcome, you logged in";
    }
}
