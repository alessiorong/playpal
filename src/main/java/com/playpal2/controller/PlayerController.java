package com.playpal2.controller;

import com.playpal2.dto.PlayerDto;
import com.playpal2.dto.PlayerStatDto;
import com.playpal2.exceptions.DuplicatePlayerException;
import com.playpal2.exceptions.EntityAlreadyExistsException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Player;
import com.playpal2.model.entity.PlayerStat;
import com.playpal2.model.service.abstraction.PlayerService;
import com.playpal2.model.service.abstraction.PlayerStatService;
import com.playpal2.utils.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("player")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerStatService playerStatService;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerStatService playerStatService) {
        this.playerService = playerService;
        this.playerStatService = playerStatService;
    }

    @GetMapping("/all-players-of-team/{teamId}")
    public ResponseEntity<?> getAllPlayerByTeamId(@PathVariable long teamId){
        try {
            List<Player> players = playerService.getAllPlayersByTeamId(teamId);
            return ResponseEntity.ok(players.stream().map(PlayerDto::new).toList());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable long id){
        try {
            Player player = playerService.getPlayerById(id);
            return ResponseEntity.ok(new PlayerDto(player));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDto playerDto){
        try {
            Player player = playerDto.toPlayer();
            playerService.createPlayer(player);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DuplicatePlayerException d){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(d.getMessage());
        }
    }

    @PostMapping("/{playerId}/add-in-team/{teamId}")
    public ResponseEntity<?> addPlayerToTeam(@PathVariable long playerId, @PathVariable long teamId, @RequestBody Map<String, Integer> request){
        try{
            int jerseyNumber = request.get("jerseyNumber");
            playerService.addPlayerToTeam(playerId, teamId, jerseyNumber);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/team/{teamId}/available-jersey-numbers")
    public ResponseEntity<List<Integer>> getAvailableJerseyNumbers(@PathVariable long teamId) {
        try {
            List<Integer> availableNumbers = playerService.getAvailableJerseyNumbers(teamId);
            return ResponseEntity.status(HttpStatus.OK).body(availableNumbers);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deletePlayerById(@PathVariable long id){
        try {
            playerService.deletePlayerById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{playerId}/new-stats")
    public ResponseEntity<?> createPlayerStatByPlayerId(@PathVariable long playerId){
        try {
             PlayerStat playerStat = playerStatService.createPlayerStatByPlayerId(playerId);
            return ResponseEntity.ok(new PlayerStatDto(playerStat));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/free")
    public ResponseEntity<List<PlayerDto>> getAllFreePlayers(){
        List<Player> freePlayerList =  playerService.getAllFreePlayers();
        return ResponseEntity.ok(freePlayerList.stream().map(PlayerDto::new).toList());
    }

    @PostMapping("/{playerId}/remove-from-team")
    public ResponseEntity<?> removePlayerFromTeam(@PathVariable long playerId){
            playerService.removePlayerFromTeam(playerId);
            return ResponseEntity.status(HttpStatus.OK).body("Player removed from team successfully");

    }

    @GetMapping("/{playerId}/total-games-played")
    public ResponseEntity<Long> getTotalGamesPlayed(@PathVariable long playerId) {
        long totalGames = playerStatService.getTotalGamesPlayed(playerId);
        return ResponseEntity.ok(totalGames);
    }

    @GetMapping("/{playerId}/average-points")
    public ResponseEntity<Double> getAveragePoints(@PathVariable long playerId){
        double averagePoints = playerStatService.calculateAveragePointsForPlayer(playerId);
        return ResponseEntity.ok(averagePoints);
    }

    @GetMapping("/{playerId}/average-offensive-rebound")
    public ResponseEntity<Double> getAverageORebound(@PathVariable long playerId){
        double averageORebound = playerStatService.calculateAverageOReboundsForPlayer(playerId);
        return ResponseEntity.ok(averageORebound);
    }

    @GetMapping("/{playerId}/average-difensive-rebound")
    public ResponseEntity<Double> getAverageDRebound(@PathVariable long playerId){
        double averageDRebound = playerStatService.calculateAverageDReboundsForPlayer(playerId);
        return ResponseEntity.ok(averageDRebound);
    }

    @GetMapping("/{playerId}/average-assist")
    public ResponseEntity<Double> getAverageAssist(@PathVariable long playerId){
        double averageAssist = playerStatService.calculateAverageAssistForPlayer(playerId);
        return ResponseEntity.ok(averageAssist);
    }

    @GetMapping("/{playerId}/average-steals")
    public ResponseEntity<Double> getAverageSteals(@PathVariable long playerId){
        double averageSteal = playerStatService.calculateAverageStealsForPlayer(playerId);
        return ResponseEntity.ok(averageSteal);
    }

    @GetMapping("/{playerId}/average-turnovers")
    public ResponseEntity<Double> getAverageTurnovers(@PathVariable long playerId){
        double averageTurnover = playerStatService.calculateAverageTurnoverForPlayer(playerId);
        return ResponseEntity.ok(averageTurnover);
    }

    @GetMapping("/{playerId}/average-blocks")
    public ResponseEntity<Double> getAverageBlocks(@PathVariable long playerId){
        double averageBlock = playerStatService.calculateAverageBlockForPlayer(playerId);
        return ResponseEntity.ok(averageBlock);
    }

    @GetMapping("/{playerId}/average-free-throw-made")
    public ResponseEntity<Double> getAverageFTM(@PathVariable long playerId){
        double averageFTM = playerStatService.calculateAverageFreeThrowMadeForPlayer(playerId);
        return ResponseEntity.ok(averageFTM);
    }

    @GetMapping("/{playerId}/average-free-throw-attempted")
    public ResponseEntity<Double> getAverageFTA(@PathVariable long playerId){
        double averageFTA = playerStatService.calculateAverageFreeThrowAttemptedForPlayer(playerId);
        return ResponseEntity.ok(averageFTA);
    }

    @GetMapping("/{playerId}/average-two-points-made")
    public ResponseEntity<Double> getAverageTwoPM(@PathVariable long playerId){
        double averageTwoPM = playerStatService.calculateAverageTwoPointsMadeForPlayer(playerId);
        return ResponseEntity.ok(averageTwoPM);
    }

    @GetMapping("/{playerId}/average-two-points-attempted")
    public ResponseEntity<Double> getAverageTwoPA(@PathVariable long playerId){
        double averageTwoPA = playerStatService.calculateAverageTwoPointsAttemptedForPlayer(playerId);
        return ResponseEntity.ok(averageTwoPA);
    }

    @GetMapping("/{playerId}/average-three-points-made")
    public ResponseEntity<Double> getAverageThreePM(@PathVariable long playerId){
        double averageThreePM = playerStatService.calculateAverageThreePointsMadeForPlayer(playerId);
        return ResponseEntity.ok(averageThreePM);
    }

    @GetMapping("/{playerId}/average-three-points-attempted")
    public ResponseEntity<Double> getAverageThreePA(@PathVariable long playerId){
        double averageThreePA = playerStatService.calculateAverageThreePointsAttemptedForPlayer(playerId);
        return ResponseEntity.ok(averageThreePA);
    }



}
