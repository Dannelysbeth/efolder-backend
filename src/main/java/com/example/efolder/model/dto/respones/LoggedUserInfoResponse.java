package com.example.efolder.model.dto.respones;

import com.example.efolder.model.User;
import com.example.efolder.model.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class LoggedUserInfoResponse {
    private String username;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;

    private Date birthdate;
    private Gender gender;

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
