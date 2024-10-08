package com.playpal2.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate gameDay;
    private String oppositeTeam;
    private String result;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team ourTeam;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerStat> stats;

    public Game() {}

    public Game(long id, LocalDate gameDay, String oppositeTeam, String result) {
        this.id = id;
        this.gameDay = gameDay;
        this.oppositeTeam = oppositeTeam;
        this.result = result;
    }
}
