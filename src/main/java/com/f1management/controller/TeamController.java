package com.f1management.controller;

import com.f1management.model.Team;
import com.f1management.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Optional<Team> getTeamById(@PathVariable Integer id) {
        return teamService.getTeamById(id);
    }

    @PostMapping
    public Team addTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Integer id, @RequestBody Map<String, String> updatedTeam) {
        String newName = updatedTeam.get("name");
        Team team = teamService.getTeamById(id).orElse(null);
        if (team == null)
            return null;
        team.setName(newName);
        return teamService.updateTeam(id, team);
    }
}
