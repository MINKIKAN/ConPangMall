package com.console.mall.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "covers")
public class Cover {
    @Id
    private Long id;
    private String url;

    // getter and setter
}
