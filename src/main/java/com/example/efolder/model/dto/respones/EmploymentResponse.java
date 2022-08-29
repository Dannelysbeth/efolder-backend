package com.example.efolder.model.dto.respones;

import com.example.efolder.model.Employment;
import com.example.efolder.model.UserInfo;
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

    private String getFullName(UserInfo userInfo){
        if(userInfo.getMiddleName()!=null)
            return userInfo.getFirstname()+" "+userInfo.getMiddleName()+" "+userInfo.getLastname();
        return userInfo.getFirstname()+" "+userInfo.getLastname();
    }

    @Builder
    public EmploymentResponse(Employment employment){
        this.username = employment.getUser().getUsername();
        this.teamName = employment.getTeam().getName();
        this.supervisor = getFullName(employment.getTeam().getTeamLeader());
        this.hrManager = getFullName(employment.getHrManager());
        this.positionName = employment.getPositionName();
        this.positionDescription = employment.getPositionDescription();
    }
}
