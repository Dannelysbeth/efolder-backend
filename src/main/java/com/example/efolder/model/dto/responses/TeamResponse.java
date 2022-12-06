package com.example.efolder.model.dto.responses;

import com.example.efolder.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
public class TeamResponse {
    Long id;
    String name;
    String description;

    @JsonProperty("teamLeader")
    String teamLeaderUsername;

    @Builder
    public TeamResponse(Team team){
        this.id = team.getId();
        this.name = team.getName();
        this.description = team.getDescription();
        if(team.getTeamLeader()!=null)
            this.teamLeaderUsername = team.getTeamLeader().getUsername();
    }


}
