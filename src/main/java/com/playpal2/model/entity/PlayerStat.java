package com.playpal2.model.entity;

import jakarta.annotation.Nullable;
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
    @Column(name = "points", nullable = false)
    private int points=0;
    @Column(name = "o_rebound", nullable = false)
    private int oRebound=0;
    @Column(name = "d_rebound",nullable = false)
    private int dRebound=0;
    @Column(name = "assist", nullable = false)
    private int assist=0;
    @Column(name = "turnover", nullable = false)
    private int turnover=0;
    @Column(name = "steal", nullable = false)
    private int steal=0;
    @Column(name = "block", nullable = false)
    private int block=0;
    @Column(name = "free_throw_made", nullable = false)
    private int freeThrowMade=0;
    @Column(name = "free_throw_attempted",nullable = false)
    private int freeThrowAttempted=0;
    @Column(name = "two_points_made",nullable = false)
    private int twoPointsMade=0;
    @Column(name = "twoPointsAttempted",nullable = false)
    private int twoPointsAttempted=0;
    @Column(name = "three_points_made", nullable = false)
    private int threePointsMade=0;
    @Column(name = "three_points_attempted",nullable = false)
    private int threePointsAttempted=0;


    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;


    public PlayerStat() {}

    public PlayerStat(long id, int points, int oRebound, int dRebound, int assist, int turnover, int steal, int block, int freeThrowMade, int freeThrowAttempted, int twoPointsMade, int twoPointsAttempted, int threePointsMade, int threePointsAttempted) {
        this.id = id;
        this.points = points;
        this.oRebound = oRebound;
        this.dRebound = dRebound;
        this.assist = assist;
        this.turnover = turnover;
        this.steal = steal;
        this.block = block;
        this.freeThrowMade = freeThrowMade;
        this.freeThrowAttempted = freeThrowAttempted;
        this.twoPointsMade = twoPointsMade;
        this.twoPointsAttempted = twoPointsAttempted;
        this.threePointsMade = threePointsMade;
        this.threePointsAttempted = threePointsAttempted;
    }
}
