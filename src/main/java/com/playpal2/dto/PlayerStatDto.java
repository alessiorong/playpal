package com.playpal2.dto;

import com.playpal2.model.entity.PlayerStat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStatDto {
    private long id;
    private String playerFirstname;
    private String playerLastname;
    private int points;
    private int rebound;
    private int assist;

    public PlayerStatDto() {}

    public PlayerStatDto(PlayerStat playerStat) {
        this.id = playerStat.getId();
        this.playerFirstname = playerStat.getPlayer().getFirstname();
        this.playerLastname = playerStat.getPlayer().getLastname();
        this.points = playerStat.getPoints();
        this.rebound = playerStat.getRebound();
        this.assist = playerStat.getAssist();
    }

    public PlayerStat toPlayerStat(){
        return new PlayerStat(this.id, this.points, this.assist, this.rebound);
    }
}
