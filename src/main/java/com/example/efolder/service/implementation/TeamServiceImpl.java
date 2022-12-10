package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.TeamIncludesEmployeesException;
import com.example.efolder.exceptions.TeamNotFoundException;
import com.example.efolder.model.Team;
import com.example.efolder.repository.TeamRepository;
import com.example.efolder.service.definition.RoleService;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final RoleService roleService;
    private final UserService userService;

    private Team updateTeamProperties(Team oldTeam, Team newTeam) {
        oldTeam.setTeamLeader(newTeam.getTeamLeader() != null ? newTeam.getTeamLeader() : oldTeam.getTeamLeader());
        oldTeam.setName(newTeam.getName() != null ? newTeam.getName() : oldTeam.getName());
        oldTeam.setEmployees(newTeam.getEmployees() != null ? newTeam.getEmployees() : oldTeam.getEmployees());
        oldTeam.setDescription(newTeam.getDescription() != null ? newTeam.getDescription() : oldTeam.getDescription());
        return oldTeam;
    }

    @Override
    public Team saveTeam(Team team) {
        team.getTeamLeader().addRole(roleService.getRole("ROLE_MANAGER"));
        userService.saveUser(team.getTeamLeader());
        return teamRepository.save(team);
    }

    @Override
    public Team getTeam(String name) {
        return teamRepository.findByName(name)
                .orElseThrow(TeamNotFoundException::new);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> getTeamsFromTeamLeader(String username) {
        return teamRepository.findAllByTeamLeader_Username(username);
    }

    @Override
    public Team updateTeam(Team team, String teamName) {
        Team t = teamRepository.findByName(teamName)
                .orElseThrow(TeamNotFoundException::new);
        return saveTeam(updateTeamProperties(t, team));
    }

    @Override
    public Team deleteTeam(String teamName) {
        Team team = teamRepository.findByName(teamName)
                .orElseThrow(TeamNotFoundException::new);
        if (team.getEmployees().isEmpty()) {
            teamRepository.delete(team);
            return team;
        }
        throw new TeamIncludesEmployeesException();

    }
}
