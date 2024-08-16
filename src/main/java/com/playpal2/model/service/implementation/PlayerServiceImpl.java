package com.playpal2.model.service.implementation;

import com.playpal2.exceptions.DuplicatePlayerException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.model.entity.Player;
import com.playpal2.model.entity.Team;
import com.playpal2.model.repository.PlayerRepo;
import com.playpal2.model.repository.TeamRepo;
import com.playpal2.model.service.abstraction.PlayerService;
import com.playpal2.utils.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepo playerRepo;
    private TeamRepo teamRepo;

    @Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo, TeamRepo teamRepo) {
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public List<Player> getAllPlayersByTeamId(long teamId) throws EntityNotFoundException {
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        if (optionalTeam.isEmpty()){
            throw new EntityNotFoundException(Team.class, teamId);
        }
        Team team = optionalTeam.get();
        return team.getPlayerList();
    }

    @Override
    public Player getPlayerById(long id) throws EntityNotFoundException {
        Optional<Player> optionalPlayer = playerRepo.findById(id);
        if (optionalPlayer.isEmpty()){
            throw new EntityNotFoundException(Player.class, id);
        }
        return optionalPlayer.get();
    }

    @Override
    public void createPlayer(Player player) throws DuplicatePlayerException {
        String firstname = player.getFirstname();
        String lastname = player.getLastname();
        if (playerRepo.existingByNameAndSurname(firstname, lastname)){
            throw new DuplicatePlayerException(player);
        }
        playerRepo.save(player);
    }

    @Override
    public void addPlayerToTeam(long playerId, long teamId) throws EntityNotFoundException {
        Optional<Player> optionalPlayer = playerRepo.findById(playerId);
        Optional<Team> optionalTeam = teamRepo.findById(teamId);
        if (optionalPlayer.isEmpty()){
            throw new EntityNotFoundException(Player.class, playerId);
        }
        if (optionalTeam.isEmpty()){
            throw new EntityNotFoundException(Team.class, teamId);
        }
        Player player = optionalPlayer.get();
        Team team = optionalTeam.get();
        if (player.getTeam()!= null){
            player.getTeam().getPlayerList().remove(player);
        }
        team.getPlayerList().add(player);
        player.setTeam(team);
        teamRepo.save(team);
        playerRepo.save(player);
    }

    @Override
    public void deletePlayerById(long id) throws EntityNotFoundException {
        Optional<Player> optionalPlayer = playerRepo.findById(id);
        if (optionalPlayer.isEmpty()){
            throw new EntityNotFoundException(Player.class, id);
        }
        Player player = optionalPlayer.get();
        playerRepo.delete(player);
    }

    @Override
    public List<Player> getAllFreePlayers() {
        return playerRepo.findByTeamIdIsNull();
    }


    @Override
    public void removePlayerFromTeam(Long playerId) {
        playerRepo.removePlayerFromTeam(playerId);
    }

}
