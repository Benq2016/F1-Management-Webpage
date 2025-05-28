package com.f1management.repository;

import com.f1management.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Integer> {
    @SuppressWarnings("null")
    @Query("SELECT m FROM Mechanic m ORDER BY m.id ASC")
    List<Mechanic> findAll();
}