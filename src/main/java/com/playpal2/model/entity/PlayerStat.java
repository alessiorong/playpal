package com.playpal2.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class PlayerStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int points;
    private int rebound;
    private int assist;


    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;


    public PlayerStat() {}

    public PlayerStat(long id, int points, int assist, int rebound) {
        this.id = id;
        this.points = points;
        this.assist = assist;
        this.rebound = rebound;
    }
}
