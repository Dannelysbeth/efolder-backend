package com.example.efolder.api;

import com.example.efolder.model.dto.requests.ChangePasswordRequest;
import com.example.efolder.model.dto.requests.CreateUserRequest;
import com.example.efolder.model.dto.requests.RoleToUserRequest;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.responses.LoggedUserInfoResponse;
import com.example.efolder.model.dto.responses.UserResponse;
import com.example.efolder.model.dto.responses.UserRolesResponse;
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

import static com.example.efolder.security.SecurityConfig.BASE_URL;

@Valid
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN"})
    @GetMapping("/all")
    public ResponseEntity<List<UserRolesResponse>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers().stream().map(
                user -> UserRolesResponse.builder()
                        .user(user)
                        .build()
        ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN", "ROLE_MANAGER"})
    @GetMapping("/employee/all")
    public ResponseEntity<List<UserResponse>> getAllEmployees() {
        return ResponseEntity.ok().body(userService.getAllUsersThatHaveRole("ROLE_REGULAR_EMPLOYEE").stream().map(
                user -> UserResponse.builder()
                        .user(user)
                        .build()
        ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN"})
    @GetMapping("/all/{roleName}")
    public ResponseEntity<List<UserResponse>> getAllUsersWithRole(@PathVariable String roleName) {
        return ResponseEntity.ok().body(userService.getAllUsersThatHaveRole(roleName).stream().map(
                user -> UserResponse.builder()
                        .user(user)
                        .build()
        ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN", "ROLE_REGULAR_EMPLOYEE"})
//    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN','ROLE_REGULAR_EMPLOYEE')")
    @GetMapping("/info")
    public ResponseEntity<LoggedUserInfoResponse> getLoggedUserInfo() {
        User loggedUser = userService.getLoggedUser();
        String picUrl = null;
        if (loggedUser.getProfilePicture() != null)
            picUrl = BASE_URL + "api/profilePicture/view/" + loggedUser.getId();
        return ResponseEntity.ok().body(LoggedUserInfoResponse.builder()
                .username(loggedUser.getUsername())
                .firstName(loggedUser.getFirstname())
                .lastName(loggedUser.getLastname())
                .email(loggedUser.getEmail())
                .middleName(loggedUser.getMiddleName())
                .imageUrl(picUrl)
                .roles(loggedUser.getRoles().stream().map(s ->
                        String.valueOf(s.getRoleName()).toString()
                ).collect(Collectors.toList()))
                .build()
        );
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN"})
    @PostMapping("/superAdmin")
    public ResponseEntity<UserRolesResponse> saveUser(@RequestBody CreateUserRequest userRequest) {
        User user = userRequest.userRequest();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/superAdmin('" + user.getUsername() + "')").toUriString());
        return ResponseEntity.created(uri).body(UserRolesResponse.builder()
                .user(userService.createSuperAdmin(user))
                .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest userRequest) {
        User user = userRequest.userRequest();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user('" + user.getUsername() + "')").toUriString());
        return ResponseEntity.created(uri).body(UserResponse.builder()
                .user(userService.createRegularEmployee(user))
                .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @PostMapping("/addRole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserRequest roleToUserRequest) {
        userService.addRoleToUser(roleToUserRequest.getUsername(), roleToUserRequest.getRoleName());
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN", "ROLE_REGULAR_EMPLOYEE"})
    @PostMapping("/changePassword")
    public ResponseEntity<UserRolesResponse> changePassword(@RequestBody ChangePasswordRequest passwordRequest) {
        User loggedUser = userService.getLoggedUser();
        loggedUser = passwordRequest.changePasswordRequest(loggedUser);             //TODO fix here this password
        return ResponseEntity.ok().body(UserRolesResponse.builder()
                .user(userService.changePassword(loggedUser.getUsername(), passwordRequest.getPassword()))
                .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN", "ROLE_REGULAR_EMPLOYEE"})
    @PostMapping("/changePassword/{username}")
    public ResponseEntity<UserRolesResponse> changeUsersPassword(@RequestBody ChangePasswordRequest passwordRequest, @PathVariable String username) {
        User user = userService.getUser(username);
        System.out.println(user.getPassword());
        user = passwordRequest.changePasswordRequest(user);
        System.out.println(user.getPassword());
        return ResponseEntity.ok().body(UserRolesResponse.builder()
                .user(userService.changePassword(user.getUsername(), user.getPassword()))
                .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_HR_ADMIN"})
    @PostMapping("/delete/{username}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String username) {
        User user = userService.getUser(username);
        if(!user.getTeams().isEmpty()){

        }
        userService.deleteUser(username);
        return ResponseEntity.ok().body(UserResponse.builder()
                .user(user)
                .build());
    }

}
