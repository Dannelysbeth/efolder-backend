package com.example.efolder.model.dto.respones;

import com.example.efolder.model.User;
import com.example.efolder.model.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
public class LoggedUserInfoResponse {
    private Long id;
    private String username;
    private String firstname;
    private String middleName;
    private String lastname;
    private String email;
    private Date birthdate;
    private Gender gender;

    @Builder
    LoggedUserInfoResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.middleName = user.getMiddleName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.birthdate = user.getBirthdate();
        this.gender = user.getGender();
    }
}
