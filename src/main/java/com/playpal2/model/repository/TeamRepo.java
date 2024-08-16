package com.playpal2.model.repository;

import com.playpal2.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Team t WHERE LOWER(t.name) = LOWER(?1)")
    boolean existsByName(String name);
}
