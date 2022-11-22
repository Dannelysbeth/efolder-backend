package com.example.efolder.service.definition;

import com.example.efolder.model.Team;

import java.util.List;

public interface TeamService {

    Team saveTeam(Team team);

    Team getTeam(String name);

    List<Team> getAllTeams();
}
