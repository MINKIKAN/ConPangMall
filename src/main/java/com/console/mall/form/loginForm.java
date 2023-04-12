package com.console.mall.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class loginForm {
    @NotEmpty(message = "아이디입력은 필수입니다")
    private String login_id;
    private String pw;

}
