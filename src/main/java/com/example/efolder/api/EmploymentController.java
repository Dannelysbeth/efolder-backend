package com.example.efolder.api;

import com.example.efolder.model.Employment;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.ChangeEmploymentRequest;
import com.example.efolder.model.dto.requests.CreateEmploymentRequest;
import com.example.efolder.model.dto.respones.EmployeeResponse;
import com.example.efolder.model.dto.respones.EmploymentResponse;
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

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN')"))
    @GetMapping("/{username}")
    public ResponseEntity<EmploymentResponse> getUserEmployment(@PathVariable String username){
        return ResponseEntity.ok().body(
                EmploymentResponse.builder()
                        .employment(employmentService.getEmployment(username))
                        .build());
    }
    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN')"))
    @PutMapping("/{username}")
    public ResponseEntity<EmploymentResponse> getUserEmployment(@PathVariable String username, @RequestBody ChangeEmploymentRequest changeEmploymentRequest){
        Employment employment = employmentService.getEmployment(username);
        employment = changeEmploymentRequest.employmentRequest(employment, teamService, userService);
        return ResponseEntity.ok().body(
                EmploymentResponse.builder()
                        .employment(employmentService.saveEmployment(employment))
                        .build());
    }
    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN", "ROLE_REGULAR_EMPLOYEE"})
    @GetMapping()
    public ResponseEntity<EmploymentResponse> getEmployment(){
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(
                EmploymentResponse.builder()
                        .employment(employmentService.getEmployment(loggedUser.getUsername()))
                        .build());
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @GetMapping("/teamEmployments")
    public ResponseEntity<List<EmploymentResponse>> getSubordinatesEmployments(){
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(employmentService.getAllBySupervisor(loggedUser.getUsername()).stream().map(
                employment ->  EmploymentResponse.builder()
                        .employment(employment)
                        .build()
                ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @GetMapping("/hrPeoplePull")
    public ResponseEntity<List<EmploymentResponse>> getHrPeoplePullEmployments(){
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(employmentService.getAllByHrManager(loggedUser.getUsername()).stream().map(
                employment ->  EmploymentResponse.builder()
                        .employment(employment)
                        .build()
        ).collect(Collectors.toList()));
    }

    @Secured({"ROLE_SUPER_ADMIN", "ROLE_MANAGER", "ROLE_HR_ADMIN"})
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok().body(employmentService.getAllEmployments().stream().map(
                employment ->  EmployeeResponse.builder()
                        .employment(employment)
                        .build()
        ).collect(Collectors.toList()));
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping()
    public ResponseEntity<EmploymentResponse>createTeam(@RequestBody CreateEmploymentRequest employmentRequest){
        Employment employment = employmentRequest.employmentRequest(userService, teamService);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/employment/"+employment.getId()).toUriString());
        return ResponseEntity.created(uri).body(EmploymentResponse.builder()
                .employment(employmentService.saveEmployment(employment))
                .build());
    }
}
