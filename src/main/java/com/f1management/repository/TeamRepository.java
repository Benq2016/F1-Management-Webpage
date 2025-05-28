package com.f1management.repository;

import com.f1management.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @SuppressWarnings("null")
    @Query("SELECT t FROM Team t ORDER BY t.id ASC")
    List<Team> findAll();
}