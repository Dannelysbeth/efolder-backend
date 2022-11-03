package com.example.efolder.model.dto.requests;

import com.example.efolder.model.Team;
import com.example.efolder.service.definition.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ChangeTeamRequest {

    @JsonProperty("teamName")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("teamLeader")
    private String teamLeaderUsername;

    private Team getChangedTeam(Team team, UserService userService){
        team.setName(name!=null? name : team.getName());
        team.setDescription(description!=null? description : team.getDescription());
        team.setTeamLeader(teamLeaderUsername!=null ? userService.getUser(teamLeaderUsername) : team.getTeamLeader());
        return team;
    }

    public Team teamRequest(Team team, UserService userInfoService){
        return getChangedTeam(team, userInfoService);
    }
}
