package com.f1management.service;

import com.f1management.model.Car;
import com.f1management.model.Participated;
import com.f1management.model.ParticipatedId;
import com.f1management.model.Race;
import com.f1management.repository.ParticipatedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipatedService {

    private final ParticipatedRepository participatedRepository;
    private final CarService carService;
    private final RaceService raceService;

    private ParticipatedService(ParticipatedRepository participatedRepository, CarService carService, RaceService raceService) {
        this.participatedRepository = participatedRepository;
        this.carService = carService;
        this.raceService = raceService;
    }

    public List<Participated> getAllParticipated() {
        return participatedRepository.findAll();
    }

    public Optional<Participated> getParticipatedById(Integer carId, Integer raceId) {
        return participatedRepository.findByCarIdAndRaceId(carId, raceId);
    }

    public Participated saveParticipated(Integer carId, Integer raceId) {
        Car car = carService.getCarById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Race race = raceService.getRaceById(raceId)
                .orElseThrow(() -> new RuntimeException("Race not found"));

        ParticipatedId id = new ParticipatedId();
        id.setCarId(carId);
        id.setRaceId(raceId);

        Participated participated = new Participated();
        participated.setId(id);
        participated.setCar(car);
        participated.setRace(race);

        return participatedRepository.save(participated);
    }

    public void deleteParticipated(Integer carId, Integer raceId) {
        participatedRepository.deleteByCarIdAndRaceId(carId, raceId);
    }
}