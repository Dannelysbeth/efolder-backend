package com.example.efolder.api;

import com.example.efolder.model.Role;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.RoleRequest;
import com.example.efolder.model.dto.requests.RoleToUserRequest;
import com.example.efolder.model.dto.responses.UserRolesResponse;
import com.example.efolder.service.definition.RoleService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN"})
    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN", "ROLE_MANAGER", "ROLE_REGULAR_EMPLOYEE"})
    @GetMapping()
    public ResponseEntity<UserRolesResponse> getLoggedUsersRoles(){
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(UserRolesResponse.builder()
                .user(loggedUser)
                .build()
        );
    }

    @Secured({"ROLE_SUPER_ADMIN"})
    @GetMapping("/{username}")
    public ResponseEntity<UserRolesResponse> getUsersRoles(@PathVariable String username){
        User user = userService.getUser(username);
        return ResponseEntity.ok().body(UserRolesResponse.builder()
                .user(user)
                .build()
        );
    }

    @Secured({"ROLE_SUPER_ADMIN"})
    @PostMapping()
    public ResponseEntity<Role>saveRole(@RequestBody RoleRequest roleRequest){
        Role role = roleRequest.roleRequest();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/"+role.getId()).toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN"})
    @PostMapping("/addRole")
    public ResponseEntity<UserRolesResponse>addRoleToUser(@RequestBody RoleToUserRequest roleToUserRequest){
        User user = roleToUserRequest.addRoleToUserRequest(userService, roleService);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/"+user.getUsername()).toUriString());
        return ResponseEntity.ok().body(UserRolesResponse.builder()
                .user(userService.addRoleToUser(user.getUsername(), roleToUserRequest.getRoleName()))
                .build()
        );
    }

}
