//package com.console.mall.controller;
//
//import com.console.mall.entitiy.Member;
//import com.console.mall.service.LoginService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//
//public class loginController {
//    @Autowired
//    private final LoginService loginService;
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//    @PostMapping("/login")
//    public String loginId(@ModelAttribute String login_id,String pw){
//        if(loginService.login(login_id,pw)){
//            return "redirect:/";
//        }
//        return "login";
//    }
//}




