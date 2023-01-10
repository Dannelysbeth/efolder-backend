package com.example.efolder.service.definition;

import com.example.efolder.model.Employment;

import java.util.List;

public interface EmploymentService {
    Employment getEmployment(String username);

    Employment saveEmployment(Employment employment);

//    List<Employment> getAllByHrManager(String hrManagerName);

    List<Employment> getAllBySupervisor(String managerUsername);

    List<Employment> getAllEmployments();

    List<Employment> getAllByTeamName(String teamName);
}
