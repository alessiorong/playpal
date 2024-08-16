package com.playpal2.model.service.implementation;

import com.playpal2.dto.GameDto;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Game;
import com.playpal2.model.entity.Team;
import com.playpal2.model.repository.GameRepo;
import com.playpal2.model.repository.TeamRepo;
import com.playpal2.model.service.abstraction.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private GameRepo gameRepo;
    private TeamRepo teamRepo;

    @Autowired
    public GameServiceImpl(GameRepo gameRepo, TeamRepo teamRepo) {
        this.gameRepo = gameRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    @Override
    public List<Game> getAllGamesByTeamId(long teamId) throws EntityNotFoundException {
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        if (optionalTeam.isEmpty()){
            throw new EntityNotFoundException(Team.class, teamId);
        }
        Team team = optionalTeam.get();
        return team.getGames();
    }

    @Override
    public Game getGameById(long id) throws EntityNotFoundException {
        Optional<Game> og = gameRepo.findById(id);
        if (og.isEmpty()){
            throw new EntityNotFoundException(Game.class, id);
        }
        return og.get();
    }

    @Override
    public void createGame(Game game, long teamId) throws EntityNotFoundException {
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        if (optionalTeam.isEmpty()){
            throw new EntityNotFoundException(Team.class, teamId);
        }
        Team team = optionalTeam.get();
        game.setOurTeam(team);
        if (game.getResult() == null){
            game.setResult("da giocare");
        }
        gameRepo.save(game);
    }

    @Override
    public void deleteGameById(long id) throws EntityNotFoundException {
        Optional<Game> optionalGame = gameRepo.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            gameRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException(Game.class, id);
        }
    }

    @Override
    public void updateResultByGameId(long gameId, String result) throws EntityNotFoundException {
        Optional<Game> optionalGame = gameRepo.findById(gameId);
        if (optionalGame.isEmpty()) {
            throw new EntityNotFoundException(Game.class, gameId);
        }
        Game game = optionalGame.get();
        game.setResult(result);
        gameRepo.save(game);
    }

}
