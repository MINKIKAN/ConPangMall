package com.console.mall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class Homecontroller {

    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "index";
    }

    
}
