package com.f1management.controller;

import com.f1management.model.Password;
import com.f1management.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> verifyPassword(@PathVariable Integer id, @RequestBody Map<String, String> payload) {
        String pin = payload.get("pin");
        Password existing = passwordService.getPasswordByTeamID(id).orElse(null);

        if (existing != null && existing.getPasscode().equals(pin))
            return ResponseEntity.ok(Map.of("valid", true));
        else if (existing == null)
            return ResponseEntity.ok(Map.of("valid", true));
        return ResponseEntity.status(403).body(Map.of("valid", false));
    }

    @DeleteMapping("/{teamID}")
    public void deletePassword(@PathVariable Integer teamID) {
        passwordService.deletePasswordByTeamID(teamID);
    }
}
