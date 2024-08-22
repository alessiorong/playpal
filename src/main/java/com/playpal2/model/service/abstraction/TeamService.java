package com.playpal2.model.service.abstraction;

import com.playpal2.exceptions.CategoryTeamException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Game;
import com.playpal2.model.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    Team getTeamById(long id) throws EntityNotFoundException;
    long getTeamIdByGameId(long gameId) throws EntityNotFoundException;
    Team addTeam(Team team) throws EntityNotFoundException, CategoryTeamException;
    void deleteTeamById(long id) throws EntityNotFoundException;
    boolean existsByName(String name);
    void updateCategoryByTeamId(long teamId, String newCategory) throws EntityNotFoundException;
    double calculateAveragePointsForTeam(long teamId);
    double calculateAverageOReboundsForTeam(long teamId);
    double calculateAverageAssistForTeam(long teamId);
    double calculateAverageDReboundsForTeam(long teamId);
    double calculateAverageStealsForTeam(long teamId);
    double calculateAverageTurnoverForTeam(long teamId);
    double calculateAverageBlockForTeam(long teamId);
    double calculateAverageFreeThrowMadeForTeam(long teamId);
    double calculateAverageFreeThrowAttemptedForTeam(long teamId);
    double calculateAverageTwoPointsMadeForTeam(long teamId);
    double calculateAverageTwoPointsAttemptedForTeam(long teamId);
    double calculateAverageThreePointsMadeForTeam(long teamId);
    double calculateAverageThreePointsAttemptedForTeam(long teamId);
}
