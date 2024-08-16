package com.playpal2.model.service.abstraction;

import com.playpal2.exceptions.DuplicatePlayerException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Game;
import com.playpal2.model.entity.Player;
import com.playpal2.utils.Position;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayersByTeamId(long teamId) throws EntityNotFoundException;
    Player getPlayerById(long id) throws EntityNotFoundException;
    void createPlayer(Player player) throws DuplicatePlayerException;
    void addPlayerToTeam(long playerId, long teamId) throws EntityNotFoundException;
    void deletePlayerById(long id) throws EntityNotFoundException;
    List<Player> getAllFreePlayers();
    void removePlayerFromTeam(Long playerId);


}
