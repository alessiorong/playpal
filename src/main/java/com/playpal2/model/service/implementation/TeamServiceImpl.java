package com.playpal2.model.service.implementation;

import com.playpal2.exceptions.CategoryTeamException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Game;
import com.playpal2.model.entity.Team;
import com.playpal2.model.repository.GameRepo;
import com.playpal2.model.repository.TeamRepo;
import com.playpal2.model.service.abstraction.TeamService;
import com.playpal2.utils.Category;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    public TeamRepo teamRepo;
    public GameRepo gameRepo;

    @Autowired
    public TeamServiceImpl(TeamRepo teamRepo, GameRepo gameRepo) {
        this.teamRepo = teamRepo;
        this.gameRepo = gameRepo;
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
}
