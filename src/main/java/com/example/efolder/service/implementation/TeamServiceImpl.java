package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.TeamIncludesEmployeesException;
import com.example.efolder.exceptions.TeamNameIsTakenException;
import com.example.efolder.exceptions.TeamNotFoundException;
import com.example.efolder.model.Role;
import com.example.efolder.model.Team;
import com.example.efolder.model.User;
import com.example.efolder.repository.TeamRepository;
import com.example.efolder.service.definition.RoleService;
import com.example.efolder.service.definition.TeamService;
import com.example.efolder.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final RoleService roleService;
    private final UserService userService;

    private Team updateTeamProperties(Team oldTeam, Team newTeam) {
        updateTeamLeader(oldTeam, newTeam);
        oldTeam.setEmployees(newTeam.getEmployees() != null ? newTeam.getEmployees() : oldTeam.getEmployees());
        oldTeam.setDescription(newTeam.getDescription() != null && !Objects.equals(newTeam.getDescription(), "") ? newTeam.getDescription() : oldTeam.getDescription());
        return oldTeam;
    }

    private boolean checkIfUsersOnlyTeam(User user) {
        return user.getTeams().size() <= 1;
    }

    private boolean checkIfTeamNameTaken(Team team) {
        return teamRepository.existsByName(team.getName());
    }

    private User deleteManagerRoleFromUser(User user, Team team) {

        if (checkIfUsersOnlyTeam(user)) {
            user.getRoles().remove(roleService.getRole("ROLE_MANAGER"));
        }
        user.getTeams().remove(team);
        return user;
    }

    private Team updateTeamLeader(Team oldTeam, Team newTeam) {
        if (oldTeam.getTeamLeader() != null &&
                newTeam.getTeamLeader() != null &&
                !Objects.equals(oldTeam.getTeamLeader().getUsername(), newTeam.getTeamLeader().getUsername())) {
            User oldTeamLeader = oldTeam.getTeamLeader();
            User newTeamLeader = newTeam.getTeamLeader();
            deleteManagerRoleFromUser(oldTeamLeader, oldTeam);
            boolean hasManagerRole = false;
            for (Role r : newTeamLeader.getRoles()) {
                if (r.getRoleName().equals("ROLE_MANAGER")) {
                    hasManagerRole = true;
                    break;
                }
            }
            if (!hasManagerRole) {
                newTeamLeader.getRoles().add(roleService.getRole("ROLE_MANAGER"));
            }
            userService.updateUser(oldTeamLeader);
            userService.updateUser(newTeamLeader);
            oldTeam.setTeamLeader(newTeamLeader);
        }
        return oldTeam;
    }

    @Override
    public Team saveTeam(Team team) {
        if (checkIfTeamNameTaken(team)) {
            throw new TeamNameIsTakenException();
        }
        team.getTeamLeader().addRole(roleService.getRole("ROLE_MANAGER"));
        userService.updateUser(team.getTeamLeader());
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
        System.out.println("here");
        return teamRepository.save(updateTeamProperties(t, team));
    }

    @Override
    public Team deleteTeam(long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
        if (team.getEmployees().isEmpty()) {
            userService.updateUser(deleteManagerRoleFromUser(team.getTeamLeader(), team));
            teamRepository.delete(team);
            return team;
        }
        throw new TeamIncludesEmployeesException();

    }
}
