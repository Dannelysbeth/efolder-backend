package com.example.efolder.service.definition;

import com.example.efolder.model.Role;

import java.util.List;

public interface RoleService {

    Role saveRole(Role role);

    Role getRole(String roleName);

    List<Role> getAllRoles();
}
