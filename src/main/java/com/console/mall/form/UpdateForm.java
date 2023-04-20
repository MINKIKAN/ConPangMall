package com.console.mall.form;


import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateForm {
//        private Long id;
        private String name;
        private String city;
        private String street;
        private String zipcode;

        private String email;

        private String phone;

        private String pw;
}
