package com.console.mall.api;

import com.console.mall.dto.MemberDTO;
import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Member;
import com.console.mall.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setLogin_id(id);
        memberDTO.setPw(pw);
        Member member = memberService.login(memberDTO);
        if (member == null) {
            return "no";
        }
        session.setAttribute("id", member.getLogin_id());
        session.setAttribute("cart", member.getCart());
        System.out.println((Cart)session.getAttribute("cart"));
        System.out.println(((Cart) session.getAttribute("cart")).getId());
        System.out.println((Cart)session.getAttribute("cart"));
        return "yes";
    }

    @PostMapping("/v1/members")
    // requset -> json -> 객체  return 객체 -> json
    public CreateMemberResponse saveMemberV1(@RequestBody Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse{
        private Long id;
    }
    @PostMapping("/v2/members")
    // requset -> json -> 객체  return 객체 -> json
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setLogin_id(request.getLogin_id());
        member.setPw(request.getPw());
        member.setPhone(request.getPhone());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    @Data
    static class CreateMemberRequest{
        @NotBlank(message = "이름입력 필수")
        private String name;
        @NotBlank(message = "이메일입력 필수")
        private String email;
        @NotBlank(message = "아이디입력 필수")
        private String login_id;
        @NotBlank(message = "비밀번호입력 필수")
        private String pw;
        @NotBlank(message = "핸드폰번호입력 필수")
        private String phone;
        @ConstructorProperties({"name","email","login_id","pw","phone"})
        public CreateMemberRequest(String name,String email,String login_id,String pw,String phone){
            this.name = name;
            this.email = email;
            this.login_id = login_id;
            this.pw = pw;
            this.phone = phone;
        }
    }


    @Data
    static class UpdateMemberRequest{
        @NotBlank(message = "이름입력 필수")
        private String name;
        private String email;
        private String login_id;
        private String pw;
        private String phone;
        @ConstructorProperties({"name","email","login_id","pw","phone"})
        public UpdateMemberRequest(String name,String email,String login_id,String pw,String phone){
            this.name = name;
            this.email = email;
            this.login_id = login_id;
            this.pw = pw;
            this.phone = phone;
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
        private String email;
        private String login_id;
        private String pw;
        private String phone;
    }


}
