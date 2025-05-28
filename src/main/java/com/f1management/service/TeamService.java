package com.f1management.service;

import com.f1management.model.Team;
import com.f1management.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Integer id) {
        return teamRepository.findById(id);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    public Team updateTeam(Integer id, Team updatedTeam) {
        return teamRepository.findById(id)
                .map(existingTeam -> {
                    existingTeam.setName(updatedTeam.getName());
                    if (updatedTeam.getDrivers() != null)
                        existingTeam.setDrivers(updatedTeam.getDrivers());
                    else existingTeam.setDrivers(new ArrayList<>());
                    if (updatedTeam.getMechanics() != null)
                        existingTeam.setMechanics(updatedTeam.getMechanics());
                    else existingTeam.setMechanics(new ArrayList<>());
                    return teamRepository.save(existingTeam);
                })
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
    }
}
