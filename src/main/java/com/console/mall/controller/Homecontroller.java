package com.console.mall.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
@Slf4j
public class Homecontroller {

    @RequestMapping("/")
    public String home(HttpServletRequest request) {
        log.info("home controller");
        HttpSession session = request.getSession();
        session.setAttribute("id", "admin");
        session.setAttribute("id", "qwer");
        return "home";
    }
}