package com.example.efolder.model.dto.requests;

import com.example.efolder.model.Team;
import com.example.efolder.service.definition.UserInfoService;
import com.example.efolder.service.definition.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class CreateTeamRequest {
    @NonNull
    @JsonProperty("teamName")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("teamLeader")
    private String teamLeaderUsername;

    public Team teamRequest(UserInfoService userService){
        return Team.builder()
                .id(null)
                .name(name)
                .description(description)
                .teamLeader(teamLeaderUsername!=null ? userService.getUser(teamLeaderUsername) : null)
                .employees(new ArrayList<>())
                .build();
    }



}
