package com.f1management.service;

import com.f1management.model.Password;
import com.f1management.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public Optional<Password> getPasswordByTeamID(Integer teamID) {
        return passwordRepository.findById(teamID);
    }

    public void savePassword(Password password) {
        passwordRepository.save(password);
    }

    public void deletePasswordByTeamID(Integer teamID) {
        passwordRepository.deleteById(teamID);
    }
}
