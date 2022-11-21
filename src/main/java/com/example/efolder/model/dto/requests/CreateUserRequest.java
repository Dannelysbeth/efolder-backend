package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.NotMatchingPasswordException;
import com.example.efolder.model.User;
import com.example.efolder.model.enums.Gender;
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
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;

    @NonNull
    @JsonProperty("lastName")
    private String lastName;

    @NonNull
    @JsonProperty("username")
    private String username;

    @NonNull
    @Email(message = "Email should be valid")
    @JsonProperty("email")
    private String email;

    @JsonProperty("birthdate")
    private Date birthdate;

//    @NonNull
    @JsonProperty("gender")
    private Gender gender;

    @NonNull
    @JsonProperty("password")
    private String password;

//    @NonNull
//    @JsonProperty("re_password")
//    private String re_password;

    /**
     * Generates a username for given name and lastname
     * @param userService
     * @return
     */
    private String createUserName(UserService userService){
        String username = (firstName.charAt(0)+ lastName).toLowerCase();
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
//    private String checkIfPasswordMatch(){
//        if(!password.equals(re_password))
//            throw new NotMatchingPasswordException();
//        else
//            return password;
//    }
    public User userRequest(UserService userService){
        return User.builder()
                .username(username)
//                .employment(null)
                .birthdate(birthdate)
                .address(null)
                .email(email)
                .address(null)
                .firstname(firstName)
                .middleName(middleName)
                .lastname(lastName)
                .gender(gender)
//                .hrPeoplePull(null)
                .password(password)
//                .password(checkIfPasswordMatch())
                .build();
    }

}
