package com.playpal2.controller;

import com.playpal2.dto.PlayerDto;
import com.playpal2.dto.TeamDto;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Team;
import com.playpal2.model.service.abstraction.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/all-teams")
    public ResponseEntity<?> getAllTeams(){
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams.stream().map(TeamDto::new).toList());
    }

    @GetMapping("by-game/{gameId}")
    public ResponseEntity<?> getTeamIdByGameId(@PathVariable long gameId){
        try {
            long teamId = teamService.getTeamIdByGameId(gameId);
            return ResponseEntity.ok(teamId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable long id){
        try {
            Team team = teamService.getTeamById(id);
            return ResponseEntity.ok(new TeamDto(team));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/exists/{name}")
    public ResponseEntity<Boolean> checkTeamNameExists(@PathVariable String name){
        boolean exists = teamService.existsByName(name);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody Team team){
        try {
            teamService.addTeam(team);
            return ResponseEntity.ok(new TeamDto(team));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteTeamById(@PathVariable long id)  {
        try{
            teamService.deleteTeamById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{teamId}/change-category")
    public ResponseEntity<?> updateCategoryByTeamId(@PathVariable long teamId, @RequestBody Map<String, String> request){
        try {
            String newCategory = request.get("category");
            teamService.updateCategoryByTeamId(teamId, newCategory);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }




}
