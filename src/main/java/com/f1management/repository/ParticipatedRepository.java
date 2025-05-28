package com.f1management.repository;

import com.f1management.model.Participated;
import com.f1management.model.ParticipatedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipatedRepository extends JpaRepository<Participated, ParticipatedId> {
    @SuppressWarnings("null")
    @Query("SELECT p FROM Participated p ORDER BY p.id.carId ASC, p.id.raceId ASC")
    List<Participated> findAll();
    Optional<Participated> findByCarIdAndRaceId(Integer carId, Integer raceId);
    void deleteByCarIdAndRaceId(Integer carId, Integer raceId);
}