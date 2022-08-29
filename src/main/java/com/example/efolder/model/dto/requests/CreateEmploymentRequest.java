package com.example.efolder.model.dto.requests;

import com.example.efolder.model.Employment;
import com.example.efolder.model.UserInfo;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;


@Getter
@AllArgsConstructor
public class CreateEmploymentRequest {

    @NonNull
    private String username;

    @NonNull
    private String teamName;

    @NonNull
    private String hrManager;

    @NonNull
    private String positionName;

    private String positionDescription;

    public Employment employmentRequest(UserInfoService userInfoService, TeamService teamService){
        UserInfo user = userInfoService.getUser(username);
        return Employment.builder()
                .id(user.getId())
                .user(user)
                .team(teamService.getTeam(teamName))
                .hrManager(userInfoService.getUser(hrManager))
                .positionName(positionName)
                .positionDescription(positionDescription)
                .build();
    }
}
