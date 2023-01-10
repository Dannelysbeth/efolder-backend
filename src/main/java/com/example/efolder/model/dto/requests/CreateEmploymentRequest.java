package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.EmptyFieldException;
import com.example.efolder.model.Employment;
import com.example.efolder.model.User;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
public class CreateEmploymentRequest {

//    @NonNull
//    private String username;

    @NotBlank
    private String teamName;

//    @NotBlank
//    private String hrManager;

    @NotBlank
    private String positionName;

    private String positionDescription;

    public boolean checkIfNotNull() {
        if (teamName == null)
            throw new EmptyFieldException("teamName");
//        if (hrManager == null)
//            throw new EmptyFieldException("hrManager");
        if (positionName == null)
            throw new EmptyFieldException("positionName");
        return true;
    }

    public Employment employmentRequest(UserService userService, TeamService teamService, String username) {
        checkIfNotNull();
        User user = userService.getUser(username);
        return Employment.builder()
                .id(user.getId())
                .user(user)
                .team(teamService.getTeam(teamName))
//                .hrManager(userService.getUser(hrManager))
                .positionName(positionName)
                .positionDescription(positionDescription)
                .build();
    }
}
