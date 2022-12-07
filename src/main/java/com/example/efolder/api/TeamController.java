package com.example.efolder.api;

import com.example.efolder.model.Team;
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

    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN')"))
    @GetMapping("/all")
    public ResponseEntity<List<TeamResponse>> getAllTeams(){
        return ResponseEntity.ok().body(teamService.getAllTeams().stream().map(
                team -> TeamResponse.builder()
                        .team(team)
                        .build()
        ).collect(Collectors.toList()));
    }
    @PreAuthorize(("hasAnyRole('ROLE_SUPER_ADMIN')"))
    @PostMapping()
    public ResponseEntity<TeamResponse>createTeam(@RequestBody CreateTeamRequest teamRequest){
        Team team = teamRequest.teamRequest(userService);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/team").toUriString());
        return ResponseEntity.created(uri).body(TeamResponse.builder()
                        .team(teamService.saveTeam(team))
                .build());
    }
}
