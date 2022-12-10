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
    String teamLeaderUsername;

    List<EmployeeResponse> employees;

    @Builder
    public TeamResponse(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.description = team.getDescription();
        if (team.getTeamLeader() != null)
            this.teamLeaderUsername = team.getTeamLeader().getUsername();
        this.employees = team.getEmployees().stream().map(employment -> EmployeeResponse.builder()
                .employment(employment)
                .build()).collect(Collectors.toList());
    }


}
