package com.console.mall.controller;

import com.console.mall.dto.MemberDTO;
import com.console.mall.entitiy.Address;
import com.console.mall.entitiy.Member;
import com.console.mall.respository.MemberRepository;
//import com.console.mall.service.LoginService;
import com.console.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        member.setEmail(form.getEmail());
        member.setLogin_id(form.getLogin_id());
        member.setPw(form.getPw());
        member.setPhone(form.getPhone());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

//    @PostMapping("members/{id}/delete")
//    public String delete(@PathVariable Long id){
//        memberService.deleteMember(id);
//        return "redirect:/members";
//    }
    @GetMapping("/members/login")
    //public String loginForm(Model model){
    //    model.addAttribute("loginForm", new loginForm());
    //    return "members/loginForm";
    //}
        public String loginForm(){
            return "login";
    }
    @PostMapping("/members/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult!=null){
            session.setAttribute("login_id",loginResult.getLogin_id());
            return "/home";
        }else{
            return "login";
        }
    }


}
