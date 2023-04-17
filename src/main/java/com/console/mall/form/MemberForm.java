package com.console.mall.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 입니다")
//    private Long id;
    private String name;
    @NotEmpty(message = "필수 항목 입니다")
    private String city;
    @NotEmpty(message = "필수 항목 입니다")
    private String street;
    @NotEmpty(message = "우편번호 필수 입니다")
    private String zipcode;
    @NotEmpty(message = "이메일은 필수 입니다")
    //@Pattern(regexp="/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/",message = "올바른 이메일주소를 입력해주세요.")
    private String email;
    @NotEmpty(message = "로그인아이디는 필수 입니다")
    @Size(min=2,max=5,message = "아이디는 2자이상 5자이하로 입력해주세요")
    private String login_id;
    @NotEmpty(message = "핸드폰번호는 필수 입니다")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phone;
    @NotEmpty(message = "비밀번호는 필수 입니다")
    @Size(min=2,max = 5,message = "비밀번호는 2자 이상 5자 이하로 입력해주세요")
    private String pw;
}