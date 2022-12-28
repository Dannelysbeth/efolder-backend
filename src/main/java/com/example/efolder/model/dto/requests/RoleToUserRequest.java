package com.example.efolder.model.dto.requests;

import com.example.efolder.exceptions.UserHasRoleException;
import com.example.efolder.model.User;
import com.example.efolder.service.definition.RoleService;
import com.example.efolder.service.definition.UserService;
import lombok.Data;

@Data
public class RoleToUserRequest {
    private String username;
    private String roleName;

    public User addRoleToUserRequest(UserService userService, RoleService roleService) {
        User user = userService.getUser(username);
        if (!user.getRoles().contains(roleService.getRole(roleName))) {
            user.getRoles().add(roleService.getRole(roleName));
            return user;
        }
        throw new UserHasRoleException(roleName, username);

    }
}
