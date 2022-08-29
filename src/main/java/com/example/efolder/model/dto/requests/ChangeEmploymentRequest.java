package com.example.efolder.model.dto.requests;

import com.example.efolder.model.Employment;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserInfoService;
import lombok.Data;


@Data
public class ChangeEmploymentRequest {
    private String teamName;

    private String hrManager;

    private String positionName;

    private String positionDescription;

    private Employment setParamsIfNull(Employment employment, TeamService teamService, UserInfoService userInfoService){
        employment.setTeam(teamName!=null ? teamService.getTeam(teamName) : employment.getTeam());
        employment.setHrManager(hrManager!=null ? userInfoService.getUser(hrManager) : employment.getHrManager());
        employment.setPositionName(positionName!=null ? positionName : employment.getPositionName());
        employment.setPositionDescription(positionDescription!=null ? positionDescription : employment.getPositionDescription());
        return employment;
    }

    public Employment employmentRequest(Employment employment, TeamService teamService, UserInfoService userInfoService){
        return setParamsIfNull(employment, teamService, userInfoService);
    }
}
