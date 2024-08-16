package com.playpal2.model.entity;

import com.playpal2.utils.Position;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    @Column(name = "jersey_number")
    private int jerseyNumber;

    private String position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    //Non ha un collegamento con game
    private List<PlayerStat> playerStats;


    public Player() {}

    public Player(String firstname, String lastname, LocalDate birthdate, int jerseyNumber, Team team) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.jerseyNumber = jerseyNumber;
        this.team = team;
    }

    public Player(long id, String firstname, String lastname, LocalDate birthdate, int jerseyNumber, String position) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
    }
}
