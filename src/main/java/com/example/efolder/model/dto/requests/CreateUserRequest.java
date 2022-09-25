package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.NotMatchingPasswordException;
import com.example.efolder.model.User;
import com.example.efolder.model.UserInfo;
import com.example.efolder.model.enums.Gender;
import com.example.efolder.service.definition.UserInfoService;
import com.example.efolder.service.definition.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Email;
import java.util.Arrays;
import java.util.Date;

@AllArgsConstructor
@Getter
public class CreateUserRequest {
    @NonNull
    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("middleName")
    private String middleName;

    @NonNull
    @JsonProperty("lastname")
    private String lastname;

    @NonNull
    @Email(message = "Email should be valid")
    @JsonProperty("email")
    private String email;

    @JsonProperty("birthdate")
    private Date birthdate;

    @NonNull
    @JsonProperty("gender")
    private Gender gender;

    @NonNull
    @JsonProperty("password")
    private String password;

    @NonNull
    @JsonProperty("repeatPassword")
    private String repeatPassword;

    /**
     * Generates a username for given name and lastname
     * @param userService
     * @return
     */
    private String createUserName(UserService userService){
        String username = (firstname.charAt(0)+lastname).toLowerCase();
        if(!userService.usernameTaken(username))
            return username;
        int usernameNumber = 1;
        username = username+usernameNumber;
        while(userService.usernameTaken(username)){
            username = Arrays.toString(username.split(String.valueOf(usernameNumber))).toString();
            usernameNumber++;
            username = username+usernameNumber;
        }
        return username;
    }
    private String checkIfPasswordMatch(){
        if(!password.equals(repeatPassword))
            throw new NotMatchingPasswordException();
        else
            return password;
    }
    public UserInfo userRequest(UserService userService){
        return UserInfo.builder()
                .username(createUserName(userService))
//                .employment(null)
                .birthdate(birthdate)
                .address(null)
                .email(email)
                .address(null)
                .firstname(firstname)
                .middleName(middleName)
                .lastname(lastname)
                .gender(gender)
//                .hrPeoplePull(null)
                .password(checkIfPasswordMatch())
                .build();
    }

}
