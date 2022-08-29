package com.example.efolder.service.definition;

import com.example.efolder.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUser(String username);

    User saveUser(User user);

    List<User> getAllUsers();

    void addRoleToUser(String username, String roleName);

    boolean usernameTaken(String username);

    User getLoggedUser();

    User changePassword(String username, String password);
}
