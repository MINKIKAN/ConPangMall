package com.console.mall.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 입니다")
//    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;
    private String email;
    private String login_id;
    private String phone;
    private String pw;
}
