package com.f1management.controller;

import com.f1management.model.Driver;
import com.f1management.model.Team;
import com.f1management.service.DriverService;
import com.f1management.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Optional<Driver> getDriverById(@PathVariable Integer id) {
        return driverService.getDriverById(id);
    }

    @PostMapping
    public Driver addDriver(@RequestBody Map<String, Object> data) {
        Integer id = (Integer) data.get("id");
        String name = (String) data.get("name");
        Integer age = (Integer) data.get("age");
        Integer teamID = (Integer) data.get("teamID");

        Team team = teamService.getTeamById(teamID).orElse(null);
        Driver driver = new Driver(id, name, age, team);
        return driverService.saveDriver(driver);
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriver(id);
    }

    @PutMapping("/{id}")
    public Driver updateDriver(@PathVariable Integer id, @RequestBody Map<String, Object> data) {
        String name = (String) data.get("name");
        Integer age = (Integer) data.get("age");
        return driverService.updateDriver(id, name, age);
    }
}
