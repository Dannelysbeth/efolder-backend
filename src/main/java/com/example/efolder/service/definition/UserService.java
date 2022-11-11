package com.example.efolder.service.definition;

import com.example.efolder.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUser(String username);

    User saveUser(User user);

    User createRegularEmployee(User user);

    List<User> getAllUsers();

    void addRoleToUser(String username, String roleName);

    boolean usernameTaken(String username);

    void  deleteUser(String username);

    User getLoggedUser();

    User updateUser(User user);

    User createSuperAdmin(User user);

//    User addSuperAdminRole(User user);
//
//    User addRegularEmployeeRole(User user);
//
//    User addManagerRole(User user);
//
//    User addHRAdminRole(User user);
//
    User changePassword(String username, String password);
}
