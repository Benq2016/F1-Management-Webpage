package com.f1management.repository;

import com.f1management.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Integer> {
    @SuppressWarnings("null")
    @Query("SELECT p FROM Password p ORDER BY p.id ASC")
    List<Password> findAll();
}
