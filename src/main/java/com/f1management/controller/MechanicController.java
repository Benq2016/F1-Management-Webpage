package com.f1management.controller;

import com.f1management.model.Mechanic;
import com.f1management.model.Team;
import com.f1management.service.MechanicService;
import com.f1management.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mechanics")
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;
    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Mechanic> getAllMechanics() {
        return mechanicService.getAllMechanics();
    }

    @GetMapping("/{id}")
    public Optional<Mechanic> getMechanicById(@PathVariable Integer id) {
        return mechanicService.getMechanicById(id);
    }

    @PostMapping
    public Mechanic addMechanic(@RequestBody Map<String, Object> data) {
        Integer id = (Integer) data.get("id");
        String name = (String) data.get("name");
        Integer salary = (Integer) data.get("salary");
        Integer teamID = (Integer) data.get("teamID");

        Team team = teamService.getTeamById(teamID).orElse(null);
        Mechanic mechanic = new Mechanic(id, name, salary, team);
        return mechanicService.saveMechanic(mechanic);
    }

    @DeleteMapping("/{id}")
    public void deleteMechanic(@PathVariable Integer id) {
        mechanicService.deleteMechanic(id);
    }

    @PutMapping("/{id}")
    public Mechanic updateMechanic(@PathVariable Integer id, @RequestBody Map<String, Object> data) {
        String name = (String) data.get("name");
        Integer salary = (Integer) data.get("salary");

        return mechanicService.updateMechanic(id, name, salary);
    }
}
