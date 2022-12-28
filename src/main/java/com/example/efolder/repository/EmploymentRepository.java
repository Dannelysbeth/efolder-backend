package com.example.efolder.repository;

import com.example.efolder.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    Optional<Employment> findByUser_Username(String username);

    List<Employment> findAllByHrManager_Username(String hrManagerUsername);

    List<Employment> findAllByTeam_Name(String teamName);

    List<Employment> findAllByTeam_TeamLeader_Username(String managerUserName);
}
