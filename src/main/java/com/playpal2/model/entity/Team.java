package com.playpal2.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String category;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> playerList;

    @OneToMany(mappedBy = "ourTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games;


    public Team() {}

    public Team(long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Team(long id, String name, String category, List<Player> playerList, List<Game> games) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.playerList = playerList;
        this.games = games;
    }
}
