package com.playpal2.model.repository;

import com.playpal2.model.entity.Player;
import com.playpal2.utils.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerRepo extends JpaRepository<Player, Long> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Player p " +
            "WHERE LOWER(p.firstname) = LOWER(:firstname) AND LOWER(p.lastname) = LOWER(:lastname)")
    boolean existingByNameAndSurname(@Param("firstname") String firstname, @Param("lastname") String lastname);

    List<Player> findByTeamIdIsNull();

    @Modifying // Questo metodo modifica il database
    @Transactional //Garantisce che avvenga interno di una transazione
    @Query("UPDATE Player p SET p.team = null WHERE p.id = :playerId")
    void removePlayerFromTeam(Long playerId);


}