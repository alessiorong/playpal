package com.playpal2.model.service.implementation;

import com.playpal2.exceptions.EntityAlreadyExistsException;
import com.playpal2.exceptions.EntityNotFoundException;
import com.playpal2.utils.Increment;
import com.playpal2.model.entity.Game;
import com.playpal2.model.entity.Player;
import com.playpal2.model.entity.PlayerStat;
import com.playpal2.model.repository.GameRepo;
import com.playpal2.model.repository.PlayerRepo;
import com.playpal2.model.repository.PlayerStatRepo;
import com.playpal2.model.service.abstraction.PlayerStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class PlayerStatServiceImpl implements PlayerStatService {
    private GameRepo gameRepo;
    private PlayerStatRepo playerStatRepo;
    private PlayerRepo playerRepo;

    @Autowired
    public PlayerStatServiceImpl(GameRepo gameRepo, PlayerStatRepo playerStatRepo, PlayerRepo playerRepo) {
        this.gameRepo = gameRepo;
        this.playerStatRepo = playerStatRepo;
        this.playerRepo = playerRepo;
    }

    @Override
    public List<PlayerStat> getPlayerStatsByGameId(long gameId) throws EntityNotFoundException {
        Optional<Game> optionalGame = gameRepo.findById(gameId);
            if (optionalGame.isEmpty()){
                throw new EntityNotFoundException(Game.class, gameId);
            }
            Game game = optionalGame.get();
            return game.getStats();
    }

    @Override
    public PlayerStat createPlayerStatByPlayerId(long playerId) throws EntityNotFoundException {
        Optional<Player> optionalPlayer = playerRepo.findById(playerId);
        if (optionalPlayer.isEmpty()){
            throw new EntityNotFoundException(Player.class, playerId);
        }
        Player player = optionalPlayer.get();
        PlayerStat playerStat = new PlayerStat();
        playerStat.setPlayer(player);
        return playerStatRepo.save(playerStat);
    }

    @Override
    public void addPlayerStatToGameByGameId(long playerStatId, long gameId) throws EntityNotFoundException, EntityAlreadyExistsException {
        Optional<Game> optionalGame = gameRepo.findById(gameId);
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(playerStatId);
        if (optionalGame.isEmpty()){
            throw new EntityNotFoundException(Game.class, gameId);
        }
        if (optionalPlayerStat.isEmpty()){
            throw new EntityNotFoundException(PlayerStat.class, playerStatId);
        }
        Game game = optionalGame.get();
        PlayerStat playerStat = optionalPlayerStat.get();

        boolean playerAlreadyHasStatInGame = game.getStats().stream().anyMatch(stat -> stat.getPlayer().getId() == playerStat.getPlayer().getId());
        if (playerAlreadyHasStatInGame){
            throw new EntityAlreadyExistsException();
        }
        if (game.getStats().contains(playerStat)){
            throw new EntityAlreadyExistsException();
        }
        playerStat.setGame(game);
        playerStatRepo.save(playerStat);
        gameRepo.save(game);

    }

    @Override
    public void addPointsByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()){
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setPoints(playerStat.getPoints() + value);
            playerStat.setFreeThrowMade(playerStat.getFreeThrowMade() + value);
            playerStat.setFreeThrowAttempted(playerStat.getFreeThrowAttempted() + value);
            playerStatRepo.save(playerStat);
        } else if (value == Increment.TWO.getValue()){
            playerStat.setPoints(playerStat.getPoints() + value);
            playerStat.setTwoPointsMade(playerStat.getTwoPointsMade() + 1);
            playerStat.setTwoPointsAttempted(playerStat.getTwoPointsAttempted()+1);
            playerStatRepo.save(playerStat);
        } else if (value == Increment.THREE.getValue()){
            playerStat.setPoints(playerStat.getPoints() + value);
            playerStat.setThreePointsMade(playerStat.getThreePointsMade()+1);
            playerStat.setThreePointsAttempted(playerStat.getThreePointsAttempted()+1);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addAssistByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setAssist(playerStat.getAssist() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addOReboundByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setORebound(playerStat.getORebound() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addDReboundByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setDRebound(playerStat.getDRebound() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addTurnoverByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setTurnover(playerStat.getTurnover() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addStealByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setSteal(playerStat.getSteal() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addBlockByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setBlock(playerStat.getBlock() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addFreeThrowMadeByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setFreeThrowMade(playerStat.getFreeThrowMade() + value);
            playerStat.setFreeThrowAttempted(playerStat.getFreeThrowAttempted() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addFreeThrowAttemptedByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setFreeThrowAttempted(playerStat.getFreeThrowAttempted() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addTwoPointsMadeByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setTwoPointsMade(playerStat.getTwoPointsMade() + value);
            playerStat.setTwoPointsAttempted(playerStat.getTwoPointsAttempted() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addTwoPointsAttemptedByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setTwoPointsAttempted(playerStat.getTwoPointsAttempted() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addThreePointsMadeByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setThreePointsMade(playerStat.getThreePointsMade() + value);
            playerStat.setThreePointsAttempted(playerStat.getThreePointsAttempted() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void addThreePointsAttemptedByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setThreePointsAttempted(playerStat.getThreePointsAttempted() + value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void removePointsByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()){
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue() || value == Increment.TWO.getValue() || value == Increment.THREE.getValue()){
            playerStat.setPoints(playerStat.getPoints() - value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void removeAssistByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setAssist(playerStat.getAssist() - value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public void removeRebpundByPlayerStatId(long statId, int value) throws EntityNotFoundException {
        Optional<PlayerStat> optionalPlayerStat = playerStatRepo.findById(statId);
        if (optionalPlayerStat.isEmpty()) {
            throw new EntityNotFoundException(PlayerStat.class, statId);
        }
        PlayerStat playerStat = optionalPlayerStat.get();
        if (value == Increment.ONE.getValue()){
            playerStat.setORebound(playerStat.getORebound() - value);
            playerStatRepo.save(playerStat);
        }
    }

    @Override
    public double calculateAveragePointsForPlayer(long playerId) {
        List<PlayerStat> stats = playerStatRepo.findallByPlayerId(playerId);

        OptionalDouble averagePoints = stats.stream()
                .mapToInt(PlayerStat::getPoints)
                .average();
        return averagePoints.isPresent() ? Math.round(averagePoints.getAsDouble() * 100.0) / 100.0 : 0;
    }

    @Override
    public double calculateAverageOReboundsForPlayer(long playerId) {
        List<PlayerStat> stats = playerStatRepo.findallByPlayerId(playerId);
        OptionalDouble averageRebound = stats.stream()
                .mapToInt(PlayerStat::getORebound)
                .average();
        return averageRebound.isPresent() ? Math.round(averageRebound.getAsDouble() * 100)/100.0 : 0;
    }

    @Override
    public double calculateAverageAssistForPlayer(long playerId) {
        List<PlayerStat> stats = playerStatRepo.findallByPlayerId(playerId);
        OptionalDouble averageAssist = stats.stream()
                .mapToInt(PlayerStat::getAssist)
                .average();
        return averageAssist.isPresent() ? Math.round(averageAssist.getAsDouble() * 100)/100.0 : 0;
    }

}







