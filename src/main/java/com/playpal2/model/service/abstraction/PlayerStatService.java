package com.playpal2.model.service.abstraction;

import com.playpal2.exceptions.EntityAlreadyExistsException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.PlayerStat;

import java.util.List;

public interface PlayerStatService {
    List<PlayerStat> getPlayerStatsByGameId(long gameId) throws EntityNotFoundException;

    PlayerStat createPlayerStatByPlayerId(long playerId) throws EntityNotFoundException;
    void addPlayerStatToGameByGameId(long playerStatId, long gameId) throws EntityNotFoundException, EntityAlreadyExistsException;
    void addPointsByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addAssistByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addReboundByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void removePointsByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void removeAssistByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void removeRebpundByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    double calculateAveragePointsForPlayer(long playerId);
    double calculateAverageReboundsForPlayer(long playerId);
    double calculateAverageAssistForPlayer(long playerId);

}