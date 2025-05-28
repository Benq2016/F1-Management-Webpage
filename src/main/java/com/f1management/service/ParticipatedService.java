package com.f1management.service;

import com.f1management.model.Participated;
import com.f1management.repository.ParticipatedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipatedService {

    private final ParticipatedRepository participatedRepository;

    private ParticipatedService(ParticipatedRepository participatedRepository) {
        this.participatedRepository = participatedRepository;
    }

    public List<Participated> getAllParticipated() {
        return participatedRepository.findAll();
    }

    public Optional<Participated> getParticipatedById(Integer carId, Integer raceId) {
        return participatedRepository.findByCarIdAndRaceId(carId, raceId);
    }

    public Participated saveParticipated(Participated participated) {
        return participatedRepository.save(participated);
    }

    public void deleteParticipated(Integer carId, Integer raceId) {
        participatedRepository.deleteByCarIdAndRaceId(carId, raceId);
    }
}