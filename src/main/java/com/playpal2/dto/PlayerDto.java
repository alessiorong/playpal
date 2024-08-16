package com.playpal2.dto;

import com.playpal2.model.entity.Player;
import com.playpal2.utils.Position;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class PlayerDto {
    private long id;
    private String firstname;
    private String lastname;
    private String birthdate;
    private int jerseyNumber;
    private String position;

    public PlayerDto() {}

    public PlayerDto(Player player) {
        this.id = player.getId();
        this.firstname = player.getFirstname();
        this.lastname = player.getLastname();
        this.birthdate = player.getBirthdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.jerseyNumber = player.getJerseyNumber();
        this.position = player.getPosition();
    }

    public Player toPlayer(){
        return new Player(this.id, this.firstname, this.lastname, LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("yyyy-MM-dd")), this.jerseyNumber, this.position);
    }
}
