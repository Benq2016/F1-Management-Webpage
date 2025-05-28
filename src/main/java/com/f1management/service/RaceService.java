package com.f1management.service;

import com.f1management.model.Race;
import com.f1management.repository.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    private RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    public Optional<Race> getRaceById(Integer id) {
        return raceRepository.findById(id);
    }

    public Race saveRace(Race race) {
        return raceRepository.save(race);
    }

    public void deleteRace(Integer id) {
        raceRepository.deleteById(id);
    }

    public Race updateRace(Integer id, Race updatedRace) {
        return raceRepository.findById(id)
                .map(existingRace -> {
                    existingRace.setName(updatedRace.getName());
                    existingRace.setLocation(updatedRace.getLocation());
                    existingRace.setDate(updatedRace.getDate());
                    existingRace.setParticipants(updatedRace.getParticipants());
                    return raceRepository.save(existingRace);
                })
                .orElseThrow(() -> new RuntimeException("Race not found with id: " + id));
    }
}
