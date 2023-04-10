package com.console.mall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class Homecontroller {

    @RequestMapping("/")
    public String home(HttpServletRequest request){
        log.info("home controller");

        return "home";
    }

    
}
