package com.example.efolder.model.dto.respones;

import com.example.efolder.model.Employment;
import com.example.efolder.model.User;
import lombok.Builder;
import lombok.Data;

import static com.example.efolder.security.SecurityConfig.BASE_URL;

@Data
public class EmployeeResponse {
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String imageUrl;
    private String teamName;
    private String supervisor;
    private String hrManager;
    private String positionName;
    private String positionDescription;

    private String getFullName(User user){
        if(user.getMiddleName()!=null)
            return user.getFirstname()+" "+user.getMiddleName()+" "+user.getLastname();
        return user.getFirstname()+" "+user.getLastname();
    }
    private String getImageUrl(User user){
        return user.getProfilePicture()==null ?
                null :
                BASE_URL+"api/profilePicture/"+user.getId();
    }

    @Builder
    public EmployeeResponse(Employment employment){
        this.id = employment.getId();
        this.firstName = employment.getUser().getFirstname();
        this.middleName = employment.getUser().getMiddleName();
        this.lastName = employment.getUser().getLastname();
        this.imageUrl = getImageUrl(employment.getUser());
        this.teamName = employment.getTeam().getName();
        this.supervisor = getFullName(employment.getTeam().getTeamLeader());
        this.hrManager = getFullName(employment.getHrManager());
        this.positionName = employment.getPositionName();
        this.positionDescription = employment.getPositionDescription();
    }
}
