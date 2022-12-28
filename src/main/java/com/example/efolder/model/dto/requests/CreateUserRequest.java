package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.EmptyFieldException;
import com.example.efolder.model.User;
import com.example.efolder.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@Getter
public class CreateUserRequest {
    @NotBlank
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;

    @NotBlank
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank
    @JsonProperty("username")
    private String username;

    @NotBlank
    @Email(message = "Email should be valid")
    @JsonProperty("email")
    private String email;

    @JsonProperty("birthdate")
    private Date birthdate;

    //    @NonNull
    @JsonProperty("gender")
    private Gender gender;

    @NotBlank
    @JsonProperty("password")
    private String password;

//    @NonNull
//    @JsonProperty("re_password")
//    private String re_password;

    public boolean checkIfNotEmpty() {
        if (firstName == null)
            throw new EmptyFieldException("firstName");
        if (lastName == null)
            throw new EmptyFieldException("lastName");
        if (username == null)
            throw new EmptyFieldException("username");
        if (email == null)
            throw new EmptyFieldException("email");
        if (password == null)
            throw new EmptyFieldException("password");
        return true;
    }

    //    /**
//     * Generates a username for given name and lastname
//     * @param userService
//     * @return
//     */
//    private String createUserName(UserService userService){
//        String username = (firstName.charAt(0)+ lastName).toLowerCase();
//
//        if(!userService.usernameTaken(username))
//            return username;
//        int usernameNumber = 1;
//        username = username+usernameNumber;
//        while(userService.usernameTaken(username)){
//            username = Arrays.toString(username.split(String.valueOf(usernameNumber))).toString();
//            usernameNumber++;
//            username = username+usernameNumber;
//        }
//        return username;
//    }
//    private String checkIfPasswordMatch(){
//        if(!password.equals(re_password))
//            throw new NotMatchingPasswordException();
//        else
//            return password;
//    }
    public User userRequest() {
        checkIfNotEmpty();
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
