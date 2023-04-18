package com.console.mall.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//@Getter
//@Setter
//public class Game {
//    private int id;
//    private String name;
//    private String summary;
//    private double total_rating;
//    private Cover cover;
//    private List<Platform> platforms;
//}
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id", referencedColumnName = "id"))
    private List<Platform> platforms;

    @Column
    private String coverUrl;

    @Column(length = 5000)
    private String summary;

    @Column
    private Long totalRatingCount;

    @OneToMany(mappedBy = "game")
    private List<ReleaseDate> releaseDates;

    @ManyToMany
    @JoinTable(name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "game_companies",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
    private List<Company> involvedCompanies;

    @ManyToMany
    @JoinTable(name = "game_modes",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "mode_id", referencedColumnName = "id"))
    private List<GameMode> gameModes;

    @OneToMany(mappedBy = "game")
    private List<Video> videos;

    @OneToMany(mappedBy = "game")
    private List<Screenshot> screenshots;

    // constructors, getters, setters, etc.
}
