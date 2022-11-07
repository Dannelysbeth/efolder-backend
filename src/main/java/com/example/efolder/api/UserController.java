package com.example.efolder.api;

import com.example.efolder.model.dto.requests.ChangePasswordRequest;
import com.example.efolder.model.dto.requests.CreateUserRequest;
import com.example.efolder.model.dto.requests.RoleToUserRequest;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.respones.LoggedUserInfoResponse;
import com.example.efolder.model.dto.respones.UserResponse;
import com.example.efolder.model.dto.respones.UserRolesResponse;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping("/all")
    public ResponseEntity<List<UserRolesResponse>>getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers().stream().map(
                user -> UserRolesResponse.builder()
                        .user(user)
                        .build()
        ).collect(Collectors.toList()));
    }
    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping("/employee/all")
    public ResponseEntity<List<UserResponse>>getAllEmployees(){
        return ResponseEntity.ok().body(userService.getAllUsers().stream().map(
                user -> UserResponse.builder()
                        .user(user)
                        .build()
        ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN", "ROLE_REGULAR_EMPLOYEE"})
    @GetMapping("/info")
    public ResponseEntity<LoggedUserInfoResponse>getLoggedUserInfo(){
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(LoggedUserInfoResponse.builder()
                        .user(loggedUser)
                        .build()
        );
    }

    @Secured({"ROLE_SUPER_ADMIN"})
    @PostMapping("/superAdmin")
    public ResponseEntity<UserRolesResponse>saveUser(@RequestBody CreateUserRequest userRequest){
        User user = userRequest.userRequest(userService);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/superAdmin('"+user.getUsername()+"')").toUriString());
        return ResponseEntity.created(uri).body(UserRolesResponse.builder()
                .user(userService.createSuperAdmin(user))
                .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @PostMapping()
    public ResponseEntity<UserResponse>create(@RequestBody CreateUserRequest userRequest){
        User user = userRequest.userRequest(userService);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user('"+user.getUsername()+"')").toUriString());
        return ResponseEntity.created(uri).body(UserResponse.builder()
                .user(userService.createRegularEmployee(user))
                .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @PostMapping("/addRole")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserRequest roleToUserRequest){
        userService.addRoleToUser(roleToUserRequest.getUsername(), roleToUserRequest.getRoleName());
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN", "ROLE_REGULAR_EMPLOYEE"})
    @PutMapping("/changePassword")
    public ResponseEntity<UserRolesResponse>changePassword(@RequestBody ChangePasswordRequest passwordRequest){
        User loggedUser = userService.getLoggedUser();
        loggedUser = passwordRequest.changePasswordRequest(loggedUser);
        return ResponseEntity.ok().body(UserRolesResponse.builder()
                .user(userService.saveUser(loggedUser))
                .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN"})
    @PutMapping("/changePassword/{username}")
    public ResponseEntity<UserRolesResponse>changeUsersPassword(@RequestBody ChangePasswordRequest passwordRequest, @PathVariable String username){
        User user = userService.getUser(username);
        user = passwordRequest.changePasswordRequest(user);
        return ResponseEntity.ok().body(UserRolesResponse.builder()
                .user(userService.saveUser(user))
                .build());
    }

}
