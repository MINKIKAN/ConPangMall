package com.console.mall.api;

import com.console.mall.dto.MemberDTO;
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
        // 기존 세션이 있는 경우, 다른 컴퓨터에서 로그인한 것으로 판단하고 로그아웃 처리
        String prevSessionId = sessionService.getSessionIdByUsername(id);
        if (prevSessionId != null && !prevSessionId.equals(session.getId())) {
            HttpSession prevSession = sessionService.getSessionById(prevSessionId);
            if (prevSession != null) {
                prevSession.removeAttribute("id");
                prevSession.setAttribute("message", "다른 컴퓨터에서 로그인하여 로그아웃 됩니다.");
                sessionService.removeSessionByUsername(id);
                SessionRegistry.removeSession(prevSessionId);
            }
        }
        SessionRegistry.addSession(session);
        session.setAttribute("id", id);

        // 새로운 세션을 등록하고 사용자 정보를 저장

        // 현재 세션 ID를 데이터베이스나 캐시에 저장
        sessionService.saveSessionIdByUsername(id, session.getId());

        return null;
    }


    @PostMapping("/v1/members")
    // requset -> json -> 객체  return 객체 -> json
    public CreateMemberResponse saveMemberV1(@RequestBody Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }

    @PostMapping("/v2/members")
    // requset -> json -> 객체  return 객체 -> json
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request, Model model) {
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
    static class CreateMemberRequest {
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

        @ConstructorProperties({"name", "email", "login_id", "pw", "phone"})
        public CreateMemberRequest(String name, String email, String login_id, String pw, String phone) {
            this.name = name;
            this.email = email;
            this.login_id = login_id;
            this.pw = pw;
            this.phone = phone;
        }
    }


    @Data
    static class UpdateMemberRequest {
        @NotBlank(message = "이름입력 필수")
        private String name;
        private String email;
        private String login_id;
        private String pw;
        private String phone;

        @ConstructorProperties({"name", "email", "login_id", "pw", "phone"})
        public UpdateMemberRequest(String name, String email, String login_id, String pw, String phone) {
            this.name = name;
            this.email = email;
            this.login_id = login_id;
            this.pw = pw;
            this.phone = phone;
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
        private String email;
        private String login_id;
        private String pw;
        private String phone;
    }


}
