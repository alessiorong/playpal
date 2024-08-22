package com.playpal2.model.service.implementation;

import com.playpal2.exceptions.CategoryTeamException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Game;
import com.playpal2.model.entity.PlayerStat;
import com.playpal2.model.entity.Team;
import com.playpal2.model.repository.GameRepo;
import com.playpal2.model.repository.PlayerStatRepo;
import com.playpal2.model.repository.TeamRepo;
import com.playpal2.model.service.abstraction.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    public TeamRepo teamRepo;
    public GameRepo gameRepo;
    public PlayerStatRepo playerStatRepo;

    @Autowired
    public TeamServiceImpl(TeamRepo teamRepo, GameRepo gameRepo, PlayerStatRepo playerStatRepo ) {
        this.teamRepo = teamRepo;
        this.gameRepo = gameRepo;
        this.playerStatRepo = playerStatRepo;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    @Override
    public Team getTeamById(long id) throws EntityNotFoundException {
        Optional<Team> optionalTeam = teamRepo.findById(id);
        if (optionalTeam.isEmpty()){
            throw new EntityNotFoundException(Team.class, id);
        }
        return optionalTeam.get();
    }

    @Override
    public long getTeamIdByGameId(long gameId) throws EntityNotFoundException {
        Optional<Game> optionalGame = gameRepo.findById(gameId);
        if (optionalGame.isEmpty()){
            throw new EntityNotFoundException(Game.class, gameId);
        }
        Game game = optionalGame.get();
        return game.getOurTeam().getId();
    }

    @Override
    public Team addTeam(Team team) throws EntityNotFoundException, CategoryTeamException {
        if (team == null){
            throw new EntityNotFoundException(Team.class, team.getId());
        }
        return teamRepo.save(team);

    }

    @Override
    public void deleteTeamById(long id) throws EntityNotFoundException {
        Optional<Team> optionalTeam = teamRepo.findById(id);
        if (optionalTeam.isEmpty()){
            throw new EntityNotFoundException(Team.class, id);
        }
        teamRepo.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return teamRepo.existsByName(name);
    }

    @Override
    public void updateCategoryByTeamId(long teamId, String newCategory) throws EntityNotFoundException {
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        if (optionalTeam.isEmpty()){
            throw new EntityNotFoundException(Team.class, teamId);
        }
        Team team = optionalTeam.get();
        team.setCategory(newCategory);
        teamRepo.save(team);
    }

    @Override
    public double calculateAveragePointsForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalPoints = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalPoints += playerStat.getPoints();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalPoints/totalGames : 0;
    }

    @Override
    public double calculateAverageOReboundsForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalOffRebounds = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalOffRebounds += playerStat.getORebound();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalOffRebounds/totalGames : 0;
    }

    @Override
    public double calculateAverageAssistForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalAssist = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalAssist += playerStat.getAssist();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalAssist/totalGames : 0;
    }

    @Override
    public double calculateAverageDReboundsForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalDefRebounds = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalDefRebounds += playerStat.getDRebound();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalDefRebounds/totalGames : 0;
    }

    @Override
    public double calculateAverageStealsForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalSteals = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalSteals += playerStat.getSteal();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalSteals/totalGames : 0;
    }

    @Override
    public double calculateAverageTurnoverForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalTurnovers = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalTurnovers += playerStat.getTurnover();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalTurnovers/totalGames : 0;
    }

    @Override
    public double calculateAverageBlockForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalBlocks = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalBlocks += playerStat.getBlock();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalBlocks/totalGames : 0;
    }

    @Override
    public double calculateAverageFreeThrowMadeForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalFTM = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalFTM += playerStat.getFreeThrowMade();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalFTM/totalGames : 0;
    }

    @Override
    public double calculateAverageFreeThrowAttemptedForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalFTA = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalFTA += playerStat.getFreeThrowAttempted();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalFTA/totalGames : 0;
    }

    @Override
    public double calculateAverageTwoPointsMadeForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalTwoPM = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalTwoPM += playerStat.getTwoPointsMade();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalTwoPM/totalGames : 0;
    }

    @Override
    public double calculateAverageTwoPointsAttemptedForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalTwoPA = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalTwoPA += playerStat.getTwoPointsAttempted();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalTwoPA/totalGames : 0;
    }

    @Override
    public double calculateAverageThreePointsMadeForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalThreePM = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalThreePM += playerStat.getThreePointsMade();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalThreePM/totalGames : 0;
    }

    @Override
    public double calculateAverageThreePointsAttemptedForTeam(long teamId) {
        List<Game> games = gameRepo.findByOurTeam_Id(teamId);
        double totalThreePA = 0;
        int totalGames = 0;
        for (Game game : games) {
            List<PlayerStat> stats = playerStatRepo.findByGame_Id(game.getId());
            for (PlayerStat playerStat : stats){
                totalThreePA += playerStat.getThreePointsAttempted();
            }
            totalGames++;
        }
        return totalGames > 0 ? totalThreePA/totalGames : 0;
    }

    @Override
    public long getTotalGamesPlayed(long teamId) {
        return teamRepo.countGamesByTeamId(teamId);
    }
}
