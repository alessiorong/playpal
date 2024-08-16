package com.playpal2.dto;

import com.playpal2.model.entity.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamDto {
    private long id;
    private String name;
    private String category;

    public TeamDto() {}

    public TeamDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.category = team.getCategory();
    }

    public Team toTeam(){
        return new Team(this.id, this.name, this.category);
    }
}
