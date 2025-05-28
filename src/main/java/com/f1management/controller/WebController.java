package com.f1management.controller;

import com.f1management.model.Team;
import com.f1management.model.Password;
import com.f1management.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class WebController {

    private final TeamService teamService;
    private final DriverService driverService;
    private final MechanicService mechanicService;
    private final CarService carService;
    private final RaceService raceService;
    private final ParticipatedService participatedService;
    private final PasswordService passwordService;

    public WebController(TeamService teamService, DriverService driverService, MechanicService mechanicService, CarService carService, RaceService raceService, ParticipatedService participatedService, PasswordService passwordService) {
        this.teamService = teamService;
        this.driverService = driverService;
        this.mechanicService = mechanicService;
        this.carService = carService;
        this.raceService = raceService;
        this.participatedService = participatedService;
        this.passwordService = passwordService;
    }

    @GetMapping("/")
    public String home() {
        return "homepage";
    }

    @GetMapping("/teams-page")
    public String teams(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("teams", teamService.getAllTeams());
        model.addAttribute("error", error != null);
        return "teams";
    }

    @GetMapping("/teams/{id}/manage")
    public String manageTeam(@PathVariable Integer id, Model model, @RequestParam("passcode") String passcode) {
        Password existing = passwordService.getPasswordByTeamID(id).orElse(null);
        if (existing == null) {
            Password newPassword = new Password(id, passcode);
            passwordService.savePassword(newPassword);
        }
        else
            if (!existing.getPasscode().equals(passcode))
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid PIN code");

        Team team= teamService.getTeamById(id)
                .orElseThrow(() -> new RuntimeException("Team not found!"));
        model.addAttribute("team", team);
        model.addAttribute("drivers", team.getDrivers());
        model.addAttribute("mechanics", team.getMechanics());
        return "manage-team";
    }

    @GetMapping("/drivers-page")
    public String drivers(Model model) {
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "drivers";
    }

    @GetMapping("/mechanics-page")
    public String mechanics(Model model) {
        model.addAttribute("mechanics", mechanicService.getAllMechanics());
        return "mechanics";
    }

    @GetMapping("/cars-page")
    public String cars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "cars";
    }

    @GetMapping("/races-page")
    public String races(Model model) {
        model.addAttribute("races", raceService.getAllRaces());
        return "races";
    }

    @GetMapping("/participations-page")
    public String participations(Model model) {
        model.addAttribute("participations", participatedService.getAllParticipated());
        return "participations";
    }
}
