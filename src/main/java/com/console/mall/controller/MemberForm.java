package com.console.mall.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 있ㅂ니다")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
