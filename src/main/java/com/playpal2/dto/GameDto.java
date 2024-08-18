package com.playpal2.dto;

import com.playpal2.model.entity.Game;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class GameDto {
    private long id;
    private String gameDay;
    private String oppositeTeam;
    private int myFinalScore;
    private int oppositeFinalScore;
    private String result;

    public GameDto() {}

    public GameDto(Game game) {
        this.id = game.getId();
        this.gameDay = game.getGameDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.oppositeTeam = game.getOppositeTeam();
        this.myFinalScore = game.getMyFinalScore();
        this.oppositeFinalScore = game.getOppositeFinalScore();
        this.result = game.getResult();
    }

    public Game toGame (){
        return new Game(this.id, LocalDate.parse(gameDay, DateTimeFormatter.ofPattern("yyyy-MM-dd")), this.oppositeTeam, this.myFinalScore, this.oppositeFinalScore, this.result);
    }
}
