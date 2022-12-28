package com.example.efolder.model.dto.responses;

import com.example.efolder.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
public class LoggedUserInfoResponse {
    private String username;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private Collection<String> roles;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthdate;
    private Gender gender;
    private String imageUrl;

//    @
//    LoggedUserInfoResponse(User user){
//        this.username = user.getUsername();
//        this.firstname = user.getFirstname();
//        this.middleName = user.getMiddleName();
//        this.lastname = user.getLastname();
//        this.email = user.getEmail();
////        this.birthdate = user.getBirthdate();
////        this.gender = user.getGender();
//    }
}
