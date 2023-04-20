package com.console.mall.controller;

import com.console.mall.entitiy.Address;
import com.console.mall.entitiy.Member;
import com.console.mall.form.MemberForm;
import com.console.mall.form.UpdateForm;
//import com.console.mall.service.LoginService;
import com.console.mall.service.CartService;
import com.console.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final CartService cartService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result, Model model) {
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
        try {
            memberService.join(member);
        }catch(IllegalStateException e){
           model.addAttribute("errorMessage",e.getMessage());
            return "members/createMemberForm";
        }
        return "redirect:/";
    }



    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/login")
    public String login() {
        return "members/loginForm";
    }

    @GetMapping("/members/updateForm")
    public String updateForm(HttpSession session,Model model ){

        String id = (String) session.getAttribute("id");
        Member member = memberService.findId(id);
        UpdateForm form = new UpdateForm();

        form.setName(member.getName());
        form.setCity(member.getAddress().getCity());
        form.setStreet(member.getAddress().getStreet());
        form.setZipcode(member.getAddress().getZipcode());
        form.setEmail(member.getEmail());
        form.setPhone(member.getPhone());
        form.setPw(member.getPw());
        model.addAttribute("form", form);
        return "members/updateForm";
    }
    @PostMapping("/members/updateForm")
    public String update(HttpSession session,@ModelAttribute("form") UpdateForm form,BindingResult result) {

        String id = (String) session.getAttribute("id");
// 이메일 유효성 검사
        if (!form.getEmail().matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            result.rejectValue("email", "invalidEmail", "올바른 이메일 주소를 입력해주세요.");
            return "members/updateForm";
        }

        // 핸드폰번호 유효성 검사
        if (!form.getPhone().matches("01[016789]-\\d{3,4}-\\d{4}")) {
            result.rejectValue("phone", "invalidPhone", "올바른 핸드폰 번호를 입력해주세요.");
            return "members/updateForm";
        }
        // 비밀번호 유효성 검사
        if (form.getPw().length() < 2 || form.getPw().length() > 5) {
            result.rejectValue("pw", "invalidPw", "비밀번호는 2자 이상 5자 이하로 입력해주세요.");
            return "members/updateForm";
        }
        memberService.update(id, form.getName(), form.getCity(), form.getStreet(), form.getZipcode(), form.getEmail(), form.getPhone(), form.getPw());

        return "redirect:/";

    }

    @GetMapping("/members/logout")
    public String logout(HttpSession session ){
        session.removeAttribute("id");
        return "redirect:/";
    }

    @GetMapping("/members/deleteForm")
    public String deleteForm(){
        return "members/deleteForm";
    }
    @ResponseBody
    @PostMapping("/members/deleteMember")
    public String delete(HttpSession session, String pw){
        String id = (String)session.getAttribute("id");
        int deleteResult = memberService.delete(id, pw);
        System.out.println("deleteResult = " + deleteResult);
        if(deleteResult != 0){
            session.removeAttribute("id");
            return "yes";
        }
        return "no";

    }
    @GetMapping("/members/delete")
    public String delete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        Member member = memberService.findOne(id);
        if (member == null) {
            redirectAttributes.addFlashAttribute("error", "해당 회원이 존재하지 않습니다.");
        } else {
            memberService.delete(member);
            redirectAttributes.addFlashAttribute("message", "회원이 삭제되었습니다.");
        }
        return "redirect:/members";
    }
}
