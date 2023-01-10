package com.example.efolder.model.dto.responses;

import com.example.efolder.model.Employment;
import com.example.efolder.model.User;
import lombok.Builder;
import lombok.Data;

@Data
public class EmploymentResponse {

    private String username;

    private String teamName;

    private String supervisor;

    private String hrManager;

    private String positionName;

    private String positionDescription;

    private String getFullName(User user) {
        if (user.getMiddleName() != null)
            return user.getFirstname() + " " + user.getMiddleName() + " " + user.getLastname();
        return user.getFirstname() + " " + user.getLastname();
    }

    @Builder
    public EmploymentResponse(Employment employment) {
        this.username = employment.getUser().getUsername();
        this.teamName = employment.getTeam().getName();
        this.supervisor = getFullName(employment.getTeam().getTeamLeader());
//        this.hrManager = getFullName(employment.getHrManager());
        this.positionName = employment.getPositionName();
        this.positionDescription = employment.getPositionDescription();
    }
}
