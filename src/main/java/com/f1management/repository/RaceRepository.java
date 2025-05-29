package com.f1management.repository;

import com.f1management.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Integer> {
    @SuppressWarnings("null")
    @Query("SELECT r FROM Race r ORDER BY r.id ASC")
    List<Race> findAll();
    List<Race> findByDateAfter(LocalDateTime date);
}
