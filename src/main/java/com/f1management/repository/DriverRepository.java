package com.f1management.repository;

import com.f1management.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @SuppressWarnings("null")
    @Query("SELECT d FROM Driver d ORDER BY d.id ASC")
    List<Driver> findAll();
}