package com.example.efolder.api;

import com.example.efolder.model.Employment;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.ChangeEmploymentRequest;
import com.example.efolder.model.dto.requests.CreateEmployeeRequest;
import com.example.efolder.model.dto.requests.CreateEmploymentRequest;
import com.example.efolder.model.dto.responses.*;
import com.example.efolder.service.definition.AddressService;
import com.example.efolder.service.definition.EmploymentService;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employment")
public class EmploymentController {
    private final EmploymentService employmentService;
    private final UserService userService;
    private final TeamService teamService;
    private final AddressService addressService;

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN')"))
    @GetMapping("/{username}")
    public ResponseEntity<EmploymentResponse> getUserEmployment(@PathVariable String username) {
        return ResponseEntity.ok().body(
                EmploymentResponse.builder()
                        .employment(employmentService.getEmployment(username))
                        .build());
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN')"))
    @PutMapping("/{username}")
    public ResponseEntity<EmploymentResponse> getUserEmployment(@PathVariable String username, @RequestBody ChangeEmploymentRequest changeEmploymentRequest) {
        Employment employment = employmentService.getEmployment(username);
        employment = changeEmploymentRequest.employmentRequest(employment, teamService, userService);
        return ResponseEntity.ok().body(
                EmploymentResponse.builder()
                        .employment(employmentService.saveEmployment(employment))
                        .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN", "ROLE_REGULAR_EMPLOYEE"})
    @GetMapping()
    public ResponseEntity<EmploymentResponse> getEmployment() {
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(
                EmploymentResponse.builder()
                        .employment(employmentService.getEmployment(loggedUser.getUsername()))
                        .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @GetMapping("/teamEmployments")
    public ResponseEntity<List<EmploymentResponse>> getSubordinatesEmployments() {
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(employmentService.getAllBySupervisor(loggedUser.getUsername()).stream().map(
                employment -> EmploymentResponse.builder()
                        .employment(employment)
                        .build()
        ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @GetMapping("/hrPeoplePull")
    public ResponseEntity<List<EmploymentResponse>> getHrPeoplePullEmployments() {
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(employmentService.getAllByHrManager(loggedUser.getUsername()).stream().map(
                employment -> EmploymentResponse.builder()
                        .employment(employment)
                        .build()
        ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok().body(employmentService.getAllEmployments().stream().map(
                employment -> EmployeeResponse.builder()
                        .employment(employment)
                        .build()
        ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<EmployeeExtendedResponse> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        createEmployeeRequest.checkIfAllRequiredAreAvailable();
        User user = createEmployeeRequest.returnBasicUser();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user('" + user.getUsername() + "')").toUriString());
        return ResponseEntity.created(uri).body(EmployeeExtendedResponse.builder()
                .user(UserResponse.builder()
                        .user(userService.createRegularEmployee(user))
                        .build())
                .address(AddressResponse.builder()
                        .address(addressService.saveAddress(createEmployeeRequest.returnBasicAddress(userService.getUser(user.getUsername()))))
                        .build())
                .employment(EmploymentResponse.builder()
                        .employment(employmentService.saveEmployment(createEmployeeRequest.returnEmployment(userService, teamService, user.getUsername())))
                        .build())
                .roles(user.getRoles().stream().map(s ->
                        String.valueOf(s.getRoleName())
                ).collect(Collectors.toList()))
                .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @GetMapping("info/{username}")
    public ResponseEntity<EmployeeExtendedResponse> viewEmployee(@PathVariable String username) {
        User user = userService.getUser(username);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user('" + username + "')").toUriString());
        return getEmployeeExtendedResponseResponseEntity(user, uri);
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_REGULAR_EMPLOYEE"})
    @GetMapping("info")
    public ResponseEntity<EmployeeExtendedResponse> viewLoggedEmployee() {
        User user = userService.getLoggedUser();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user('" + user.getUsername() + "')").toUriString());
        return getEmployeeExtendedResponseResponseEntity(user, uri);
    }


    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping("/{username}")
    public ResponseEntity<EmploymentResponse> createTeam(@RequestBody CreateEmploymentRequest employmentRequest, @PathVariable String username) {
        Employment employment = employmentRequest.employmentRequest(userService, teamService, username);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/employment/" + employment.getId()).toUriString());
        return ResponseEntity.created(uri).body(EmploymentResponse.builder()
                .employment(employmentService.saveEmployment(employment))
                .build());
    }

    private ResponseEntity<EmployeeExtendedResponse> getEmployeeExtendedResponseResponseEntity(User user, URI uri) {
        return ResponseEntity.created(uri).body(EmployeeExtendedResponse.builder()
                .user(UserResponse.builder()
                        .user(user)
                        .build())
                .address(AddressResponse.builder()
                        .address(user.getAddress())
                        .build())
                .employment(EmploymentResponse.builder()
                        .employment(user.getEmployment())
                        .build())
                .roles(user.getRoles().stream().map(s ->
                        String.valueOf(s.getRoleName())
                ).collect(Collectors.toList()))
                .build());
    }
}
