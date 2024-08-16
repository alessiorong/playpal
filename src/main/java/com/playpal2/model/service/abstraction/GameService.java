package com.playpal2.model.service.abstraction;

import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    List<Game> getAllGamesByTeamId(long teamId) throws EntityNotFoundException;
    Game getGameById(long id) throws EntityNotFoundException;
    void createGame(Game game, long teamId) throws EntityNotFoundException;
    void deleteGameById(long id) throws EntityNotFoundException;
    void updateResultByGameId(long gameId, String result) throws EntityNotFoundException;




}
