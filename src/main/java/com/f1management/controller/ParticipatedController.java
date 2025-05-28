package com.f1management.controller;

import com.f1management.model.Participated;
import com.f1management.model.ParticipatedId;
import com.f1management.service.ParticipatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participations")
public class ParticipatedController {

    @Autowired
    private ParticipatedService participatedService;

    @GetMapping
    public List<Participated> getAllParticipated() {
        return participatedService.getAllParticipated();
    }

    @GetMapping("/{carId}/{raceId}")
    public Optional<Participated> getParticipatedById(@PathVariable Integer carId, @PathVariable Integer raceId) {
        return participatedService.getParticipatedById(carId, raceId);
    }

    @PostMapping
    public ResponseEntity<Participated> addParticipation(@RequestBody ParticipatedId request) {
        Participated newParticipation = participatedService.saveParticipated(request.getCarId(), request.getRaceId());
        return ResponseEntity.ok(newParticipation);
    }

    @DeleteMapping("/{carId}/{raceId}")
    public void deleteParticipated(@PathVariable Integer carId, @PathVariable Integer raceId) {
        participatedService.deleteParticipated(carId, raceId);
    }
}