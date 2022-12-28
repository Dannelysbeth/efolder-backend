package com.example.efolder.repository;

import com.example.efolder.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);

    List<Team> findAllByTeamLeader_Username(String leaderUsername);

    boolean existsByName(String teamName);
}
