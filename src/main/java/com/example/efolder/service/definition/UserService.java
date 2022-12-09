package com.example.efolder.service.definition;

import com.example.efolder.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUser(String username);

    User saveUser(User user);

    User createRegularEmployee(User user);

    List<User> getAllUsers();

    User addRoleToUser(String username, String roleName);

    boolean usernameTaken(String username);

    void  deleteUser(String username);

    User getLoggedUser();

    User updateUser(User user);

    User createSuperAdmin(User user);

    boolean checkIfUserHasRole(String username, String roleName);

    User deleteRoleFromUser(String username, String roleName);

    User changePassword(String username, String password);

    List<User> getAllUsersThatHaveRole(String roleName);
}
