package com.example.efolder.service.implementation;

import com.example.efolder.exceptions.TeamNotFoundException;
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

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final RoleService roleService;
    private final UserService userService;

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
}
