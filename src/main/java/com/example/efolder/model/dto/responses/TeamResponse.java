package com.example.efolder.model.dto.responses;

import com.example.efolder.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeamResponse {
    Long id;
    String name;
    String description;

    @JsonProperty("teamLeader")
    EmployeeResponse teamLeaderUsername;

    List<EmployeeResponse> employees;

    int teamSize;

    private int countTeamMembers(Team team) {
        int teamSize = 0;
        if (!team.getEmployees().isEmpty()) {
            for (int i = 0; i < team.getEmployees().size(); i++) {
                teamSize++;
            }
        }
        return teamSize;
    }

    @Builder
    public TeamResponse(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.description = team.getDescription();
        this.teamSize = countTeamMembers(team);
        if (team.getTeamLeader() != null)
            this.teamLeaderUsername = EmployeeResponse.builder()
                    .employment(team.getTeamLeader().getEmployment())
                    .build();
        this.employees = team.getEmployees().stream().map(employment -> EmployeeResponse.builder()
                .employment(employment)
                .build()).collect(Collectors.toList());
    }


}
