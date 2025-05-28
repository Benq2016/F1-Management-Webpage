package com.f1management.controller;

import com.f1management.model.Race;
import com.f1management.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

    @GetMapping
    public List<Race> getAllRaces() {
        return raceService.getAllRaces();
    }

    @GetMapping("/{id}")
    public Optional<Race> getRaceById(@PathVariable Integer id) {
        return raceService.getRaceById(id);
    }

    @PostMapping
    public Race addRace(@RequestBody Race race) {
        return raceService.saveRace(race);
    }

    @DeleteMapping("/{id}")
    public void deleteRace(@PathVariable Integer id) {
        raceService.deleteRace(id);
    }

    @PutMapping("/{id}")
    public Race updateRace(@PathVariable Integer id, @RequestBody Race updatedRace) {
        return raceService.updateRace(id, updatedRace);
    }
}
