package com.console.mall.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "covers")
@Getter
@Setter
public class Cover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    // constructors, getters, setters, etc.
}