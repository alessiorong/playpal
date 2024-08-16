package com.playpal2.controller;

import com.playpal2.dto.PlayerDto;
import com.playpal2.dto.PlayerStatDto;
import com.playpal2.exceptions.DuplicatePlayerException;
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

import java.util.List;
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
    public ResponseEntity<?> addPlayerToTeam(@PathVariable long playerId, @PathVariable long teamId){
        try{
            playerService.addPlayerToTeam(playerId, teamId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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

    @GetMapping("/{playerId}/average-points")
    public ResponseEntity<Double> getAveragePoints(@PathVariable long playerId){
        double averagePoints = playerStatService.calculateAveragePointsForPlayer(playerId);
        return ResponseEntity.ok(averagePoints);
    }

    @GetMapping("/{playerId}/average-rebound")
    public ResponseEntity<Double> getAverageRebound(@PathVariable long playerId){
        double averageRebound = playerStatService.calculateAverageReboundsForPlayer(playerId);
        return ResponseEntity.ok(averageRebound);
    }

    @GetMapping("/{playerId}/average-assist")
    public ResponseEntity<Double> getAverageAssist(@PathVariable long playerId){
        double averageAssist = playerStatService.calculateAverageAssistForPlayer(playerId);
        return ResponseEntity.ok(averageAssist);
    }



}
