package com.example.efolder.model.dto.responses;

import com.example.efolder.model.Role;
import com.example.efolder.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
public class UserRolesResponse {
    Long id;
    String username;
    Collection<Role> roles;

    @Builder
    public UserRolesResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getRoles();
    }
}
