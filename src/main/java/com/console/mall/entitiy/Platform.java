package com.console.mall.entitiy;

import javax.persistence.*;

@Entity
@Table(name = "platforms")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // constructors, getters, setters, etc.
}