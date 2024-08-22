package com.playpal2.model.service.abstraction;

import com.playpal2.exceptions.EntityAlreadyExistsException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.PlayerStat;

import java.util.List;

public interface PlayerStatService {
    List<PlayerStat> getPlayerStatsByGameId(long gameId) throws EntityNotFoundException;
    PlayerStat createPlayerStatByPlayerId(long playerId) throws EntityNotFoundException;
    void addPlayerStatToGameByGameId(long playerStatId, long gameId) throws EntityNotFoundException, EntityAlreadyExistsException;
    void removePlayerStatByPlayerId(long gameId, long playerStatId) throws EntityNotFoundException;
    long getTotalGamesPlayed(long playerId);
    void addPointsByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addAssistByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addOReboundByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addDReboundByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addTurnoverByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addStealByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addBlockByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addFreeThrowMadeByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addFreeThrowAttemptedByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addTwoPointsMadeByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addTwoPointsAttemptedByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addThreePointsMadeByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void addThreePointsAttemptedByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void removePointsByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void removeAssistByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    void removeReboundByPlayerStatId(long statId, int value) throws EntityNotFoundException;
    double calculateAveragePointsForPlayer(long playerId);
    double calculateAverageOReboundsForPlayer(long playerId);
    double calculateAverageAssistForPlayer(long playerId);
    double calculateAverageDReboundsForPlayer(long playerId);
    double calculateAverageStealsForPlayer(long playerId);
    double calculateAverageTurnoverForPlayer(long playerId);
    double calculateAverageBlockForPlayer(long playerId);
    double calculateAverageFreeThrowMadeForPlayer(long playerId);
    double calculateAverageFreeThrowAttemptedForPlayer(long playerId);
    double calculateAverageTwoPointsMadeForPlayer(long playerId);
    double calculateAverageTwoPointsAttemptedForPlayer(long playerId);
    double calculateAverageThreePointsMadeForPlayer(long playerId);
    double calculateAverageThreePointsAttemptedForPlayer(long playerId);
}