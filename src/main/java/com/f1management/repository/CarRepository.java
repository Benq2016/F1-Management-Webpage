package com.f1management.repository;

import com.f1management.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @SuppressWarnings("null")
    @Query("SELECT c FROM Car c ORDER BY c.id ASC")
    List<Car> findAll();
}