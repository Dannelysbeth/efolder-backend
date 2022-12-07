package com.example.efolder.model.dto.responses;

import com.example.efolder.model.User;
import com.example.efolder.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

import static com.example.efolder.security.SecurityConfig.BASE_URL;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String firstname;
    private String middleName;
    private String lastname;
    private String email;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthdate;
    private Gender gender;
//    private String password;

    private String imageUrl;

    @Builder
    UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.middleName = user.getMiddleName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.birthdate = user.getBirthdate();
        this.gender = user.getGender();
//        this.password = user.getPassword();
        if(user.getProfilePicture() != null)
            this.imageUrl = BASE_URL+"api/profilePicture/view/"+user.getId();
        else
            this.imageUrl = null;
    }
}
