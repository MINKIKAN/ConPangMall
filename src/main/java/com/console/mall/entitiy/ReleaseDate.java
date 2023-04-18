package com.console.mall.entitiy;

import javax.persistence.*;

@Entity
@Table(name = "release_dates")
public class ReleaseDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    @Column
    private String human;

    // constructors, getters, setters, etc.
}