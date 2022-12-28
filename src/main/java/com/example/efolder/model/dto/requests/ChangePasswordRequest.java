package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.NotMatchingPasswordException;
import com.example.efolder.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordRequest {
    String password;
    String repeatPassword;

    private String checkIfPasswordMatch() {
        if (!password.equals(repeatPassword))
            throw new NotMatchingPasswordException();
        else
            return password;
    }

    public User changePasswordRequest(User user) {
        user.setPassword(checkIfPasswordMatch());
        return user;
    }
}
