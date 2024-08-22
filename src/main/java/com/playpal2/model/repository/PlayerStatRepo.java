package com.playpal2.model.repository;

import com.playpal2.model.entity.Player;
import com.playpal2.model.entity.PlayerStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerStatRepo extends JpaRepository<PlayerStat, Long> {

@Query("SELECT p FROM PlayerStat p WHERE p.player.id = :playerId")
List<PlayerStat> findallByPlayerId(@Param("playerId") long playerId);

Optional<PlayerStat> findByPlayer(Player player);
List<PlayerStat> findByGame_Id(long gameId);
}
