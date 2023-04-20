package com.console.mall.api;

import com.console.mall.dto.MemberDTO;
import com.console.mall.entitiy.Address;
import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Member;
import com.console.mall.service.MemberService;

import com.console.mall.session.SessionRegistry;
import com.console.mall.session.SessionService;
import com.console.mall.session.SessionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;
    private final SessionServiceImpl sessionService;

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session, Model model) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setLogin_id(id);
        memberDTO.setPw(pw);
        Member member = memberService.login(memberDTO);
        if (member == null) {
            return "no";
        }
        loginCheck(id, session);
        return "yes";
    }


    private String loginCheck(String id, HttpSession session) {
        String prevSessionId = sessionService.getSessionIdByUsername(id);
        if (prevSessionId != null && !prevSessionId.equals(session.getId())) {
            HttpSession prevSession = sessionService.getSessionById(prevSessionId);
            if (prevSession != null) {
                prevSession.removeAttribute("id");
                prevSession.setAttribute("msg", "success");
                sessionService.removeSessionByUsername(id);
                SessionRegistry.removeSession(prevSessionId);
            }
        }
        SessionRegistry.addSession(session);
        session.setAttribute("id", id);

        sessionService.saveSessionIdByUsername(id, session.getId());

        return null;
    }
    @PostMapping("/address")
    public String addUpdate(@RequestParam("street") String street,
                            @RequestParam("zipcode") String zipcode,
                            @RequestParam("address") String city,
                            HttpSession session) {
        Member member = memberService.findId((String)session.getAttribute("id"));
        Address newAddress = new Address(city,street,zipcode);
        System.out.println(newAddress.toString());
        System.out.println(newAddress.toString());
        System.out.println(newAddress.toString());
        System.out.println(newAddress.toString());
        System.out.println(newAddress.toString());
        System.out.println(newAddress.toString());
        member.setAddress(newAddress);
        memberService.save(member);

        return null;
    }

}
