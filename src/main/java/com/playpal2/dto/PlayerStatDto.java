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
    private int oRebound;
    private int dRebound;
    private int assist;
    private int turnover;
    private int steal;
    private int block;
    private int freeThrowMade;
    private int freeThrowAttempted;
    private int twoPointsMade;
    private int twoPointsAttempted;
    private int threePointsMade;
    private int threePointsAttempted;

    public PlayerStatDto() {}

    public PlayerStatDto(PlayerStat playerStat) {
        this.id = playerStat.getId();
        this.playerFirstname = playerStat.getPlayer().getFirstname();
        this.playerLastname = playerStat.getPlayer().getLastname();
        this.points = playerStat.getPoints();
        this.oRebound = playerStat.getORebound();
        this.dRebound = playerStat.getDRebound();
        this.assist = playerStat.getAssist();
        this.turnover = playerStat.getTurnover();
        this.steal = playerStat.getSteal();
        this.block = playerStat.getBlock();
        this.freeThrowMade = playerStat.getFreeThrowMade();
        this.freeThrowAttempted = playerStat.getFreeThrowAttempted();
        this.twoPointsMade = playerStat.getTwoPointsMade();
        this.twoPointsAttempted = playerStat.getTwoPointsAttempted();
        this.threePointsMade = playerStat.getThreePointsMade();
        this.threePointsAttempted = playerStat.getThreePointsAttempted();
    }

    public PlayerStat toPlayerStat(){
        return new PlayerStat(this.id, this.points, this.oRebound, this.dRebound, this.assist, this.turnover,
                this.steal, this.block, this.freeThrowMade, this.freeThrowAttempted,
                this.twoPointsMade, this.twoPointsAttempted, this.threePointsMade, this.threePointsAttempted);
    }
}
