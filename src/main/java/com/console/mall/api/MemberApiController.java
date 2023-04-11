package com.console.mall.api;

import com.console.mall.entitiy.Member;
import com.console.mall.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();   /// 객체 리스트 반환 -> json 메시지컴버터 작동 -> json 리스트로 넘겨준다
    }
    @GetMapping("/v2/members")
    public Result membersV2(){
        List<Member> members = memberService.findMembers(); // 순수 member 클래스의 리스트 member -> memberDTO
        List<MemberDto> list = members.stream().map(m-> new MemberDto(m.getName())).collect(Collectors.toList());

        return new Result(list.size(),list);
    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;

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


    // 부분 수정
//    @PatchMapping("/v2/members/{id}")
//    public UpdateMemberResponse updateMember(
//            @PathVariable Long id,
//            @RequestBody UpdateMemberRequest request ){
//
//        // 수정할때는 변경감지사용하자
//        memberService.update(id,request.getName(), request.getEmail(), request.getLogin_id(), request.getPw(), request.getPhone());  // 커멘드 -> 수정
//        // 쿼리문 따로 부르기
//        Member member = memberService.findOne(id);
//        return new UpdateMemberResponse
//                (member.getId(), member.getName(),member.getEmail(),member.getLogin_id(),member.getPw(),member.getPhone());
//    }
}
