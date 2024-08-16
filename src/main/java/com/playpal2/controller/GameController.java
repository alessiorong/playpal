package com.playpal2.controller;

import com.playpal2.dto.GameDto;
import com.playpal2.dto.PlayerStatDto;
import com.playpal2.exceptions.EntityAlreadyExistsException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Game;
import com.playpal2.model.entity.PlayerStat;
import com.playpal2.model.service.abstraction.GameService;
import com.playpal2.model.service.abstraction.PlayerStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {
    public final GameService gameService;
    public final PlayerStatService playerStatService;

    @Autowired
    public GameController(GameService gameService, PlayerStatService playerStatService) {
        this.gameService = gameService;
        this.playerStatService = playerStatService;
    }

    @GetMapping("/all-games")
    public ResponseEntity<?> getAllGames(){
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games.stream().map(GameDto::new).toList());
    }

    @GetMapping("/all-games-from-team/{teamId}")
    public ResponseEntity<?> getAllGamesByTeamId(@PathVariable long teamId){
        try{
            List<Game> games = gameService.getAllGamesByTeamId(teamId);
            return ResponseEntity.ok(games.stream().map(GameDto::new).toList());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team non trovato");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable long id){
        try {
            Game game = gameService.getGameById(id);
            return ResponseEntity.ok(new GameDto(game));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/new-game/{teamId}")
    public ResponseEntity<?> createGameByTeamId(@RequestBody GameDto gameDto, @PathVariable long teamId){
        try {
            gameService.createGame(gameDto.toGame(), teamId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-game/{id}")
    public ResponseEntity<?> deleteGameById(@PathVariable long id){
        try {
            gameService.deleteGameById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{gameId}/game-stats")
    public ResponseEntity<?> getPlayerStatsByGameId(@PathVariable long gameId){
        try {
            List<PlayerStat> stats = playerStatService.getPlayerStatsByGameId(gameId);
            return ResponseEntity.ok(stats.stream().map(PlayerStatDto::new).toList());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{gameId}/add-playerstat/{playerStatId}")
    public ResponseEntity<?> addPlayerStatToGame(@PathVariable long playerStatId, @PathVariable long gameId){
        try {
            playerStatService.addPlayerStatToGameByGameId(playerStatId, gameId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/playerstat/{statId}/add-points")
    public ResponseEntity<?> addPoints(@PathVariable long statId, @RequestParam int value){
        try {
            playerStatService.addPointsByPlayerStatId(statId, value);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/playerstat/{statId}/add-assist")
    public ResponseEntity<?> addAssist(@PathVariable long statId, @RequestParam int value){
        try{
            playerStatService.addAssistByPlayerStatId(statId, value);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/playerstat/{statId}/add-rebound")
    public ResponseEntity<?> addRebound(@PathVariable long statId, @RequestParam int value){
        try{
            playerStatService.addReboundByPlayerStatId(statId, value);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{gameId}/change-result")
    public ResponseEntity<?> updateResult(@PathVariable long gameId, @RequestBody Map<String, String> request){
        try {
            String result = request.get("result");
            gameService.updateResultByGameId(gameId, result);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }






}
