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

    @GetMapping("/{teamId}/total-games-played")
    public long getTotalGamesPlayed(@PathVariable long teamId) {
        return teamService.getTotalGamesPlayed(teamId);
    }

    @GetMapping("/{teamId}/average-points")
    public ResponseEntity<Double> getAveragePoints(@PathVariable long teamId){
        double averagePoints = teamService.calculateAveragePointsForTeam(teamId);
        return ResponseEntity.ok(averagePoints);
    }

    @GetMapping("/{teamId}/average-offensive-rebounds")
    public ResponseEntity<Double> getAverageOffRebounds(@PathVariable long teamId){
        double averageOffRebounds = teamService.calculateAverageOReboundsForTeam(teamId);
        return ResponseEntity.ok(averageOffRebounds);
    }

    @GetMapping("/{teamId}/average-assist")
    public ResponseEntity<Double> getAverageAssist(@PathVariable long teamId){
        double averageAssist = teamService.calculateAverageAssistForTeam(teamId);
        return ResponseEntity.ok(averageAssist);
    }

    @GetMapping("/{teamId}/average-defensive-rebounds")
    public ResponseEntity<Double> getAverageDefRebounds(@PathVariable long teamId){
        double averageDefRebounds = teamService.calculateAverageDReboundsForTeam(teamId);
        return ResponseEntity.ok(averageDefRebounds);
    }

    @GetMapping("/{teamId}/average-steals")
    public ResponseEntity<Double> getAverageSteals(@PathVariable long teamId){
        double averageSteals = teamService.calculateAverageStealsForTeam(teamId);
        return ResponseEntity.ok(averageSteals);
    }

    @GetMapping("/{teamId}/average-turnovers")
    public ResponseEntity<Double> getAverageTurnovers(@PathVariable long teamId){
        double averageTurnovers = teamService.calculateAverageTurnoverForTeam(teamId);
        return ResponseEntity.ok(averageTurnovers);
    }

    @GetMapping("/{teamId}/average-blocks")
    public ResponseEntity<Double> getAverageBlocks(@PathVariable long teamId){
        double averageBlocks = teamService.calculateAverageBlockForTeam(teamId);
        return ResponseEntity.ok(averageBlocks);
    }

    @GetMapping("/{teamId}/average-free-throw-made")
    public ResponseEntity<Double> getAverageFTM(@PathVariable long teamId){
        double averageFTM = teamService.calculateAverageFreeThrowMadeForTeam(teamId);
        return ResponseEntity.ok(averageFTM);
    }

    @GetMapping("/{teamId}/average-free-throw-attempted")
    public ResponseEntity<Double> getAverageFTA(@PathVariable long teamId){
        double averageFTA = teamService.calculateAverageFreeThrowAttemptedForTeam(teamId);
        return ResponseEntity.ok(averageFTA);
    }

    @GetMapping("/{teamId}/average-two-points-made")
    public ResponseEntity<Double> getAverageTwoPM(@PathVariable long teamId){
        double averageTwoPM = teamService.calculateAverageTwoPointsMadeForTeam(teamId);
        return ResponseEntity.ok(averageTwoPM);
    }

    @GetMapping("/{teamId}/average-two-points-attempted")
    public ResponseEntity<Double> getAverageTwoPA(@PathVariable long teamId){
        double averageTwoPA = teamService.calculateAverageTwoPointsAttemptedForTeam(teamId);
        return ResponseEntity.ok(averageTwoPA);
    }

    @GetMapping("/{teamId}/average-three-points-made")
    public ResponseEntity<Double> getAverageThreePM(@PathVariable long teamId){
        double averageThreePM = teamService.calculateAverageThreePointsMadeForTeam(teamId);
        return ResponseEntity.ok(averageThreePM);
    }

    @GetMapping("/{teamId}/average-three-points-attempted")
    public ResponseEntity<Double> getAverageThreePA(@PathVariable long teamId){
        double averageThreePA = teamService.calculateAverageThreePointsAttemptedForTeam(teamId);
        return ResponseEntity.ok(averageThreePA);
    }

}
