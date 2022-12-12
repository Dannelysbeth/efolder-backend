package com.example.efolder.api;

import com.example.efolder.model.Team;
import com.example.efolder.model.User;
import com.example.efolder.model.dto.requests.CreateTeamRequest;
import com.example.efolder.model.dto.responses.TeamResponse;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;
    private final UserService userService;

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_MANAGER')"))
    @GetMapping("/{teamName}")
    public ResponseEntity<TeamResponse> getTeamByName(@PathVariable String teamName) {
        Team team = teamService.getTeam(teamName);
        return ResponseEntity.ok().body(TeamResponse.builder()
                .team(team)
                .build());
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @GetMapping("/all")
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        return ResponseEntity.ok().body(teamService.getAllTeams().stream().map(
                team -> TeamResponse.builder()
                        .team(team)
                        .build()
        ).collect(Collectors.toList()));
    }

    @PreAuthorize(("hasAnyRole('ROLE_MANAGER')"))
    @GetMapping("/teamLeader/{username}")
    public ResponseEntity<List<TeamResponse>> getTeamsFromTeamLeader(@PathVariable String username) {
        return ResponseEntity.ok().body(teamService.getTeamsFromTeamLeader(username).stream().map(
                team -> TeamResponse.builder()
                        .team(team)
                        .build()
        ).collect(Collectors.toList()));
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN', 'ROLE_MANAGER')"))
    @GetMapping("/myTeams")
    public ResponseEntity<List<TeamResponse>> getTeamsFromLoggedTeamLeader() {
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.ok().body(teamService.getTeamsFromTeamLeader(loggedUser.getUsername()).stream().map(
                team -> TeamResponse.builder()
                        .team(team)
                        .build()
        ).collect(Collectors.toList()));
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping()
    public ResponseEntity<TeamResponse> createTeam(@RequestBody CreateTeamRequest teamRequest) {
        Team team = teamRequest.teamRequest(userService);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/team").toUriString());
        return ResponseEntity.created(uri).body(TeamResponse.builder()
                .team(teamService.saveTeam(team))
                .build());
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping("/{teamName}")
    public ResponseEntity<TeamResponse> updateTeamByName(@PathVariable String teamName, @RequestBody CreateTeamRequest teamRequest) {
        Team team = teamRequest.teamRequest(userService);
        return ResponseEntity.ok().body(TeamResponse.builder()
                .team(teamService.updateTeam(team, teamName))
                .build());
    }

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
    @PostMapping("delete/{id}")
    public ResponseEntity<TeamResponse> deleteTeamByName(@PathVariable long id) {
        return ResponseEntity.ok().body(TeamResponse.builder()
                .team(teamService.deleteTeam(id))
                .build());
    }
//    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_HR_ADMIN')"))
//    @PostMapping("user/delete/{teamName}")
//    public ResponseEntity<TeamResponse> deleteUserFromTeam(@PathVariable String teamName) {
//        return ResponseEntity.ok().body(TeamResponse.builder()
//                .team(teamService.deleteTeam(teamName))
//                .build());
//    }
}
