package com.f1management.controller;

import com.f1management.model.Password;
import com.f1management.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/verify")
    public void verifyOrSavePassword(@RequestBody Map<String, String> payload) {
        Integer teamID = Integer.parseInt(payload.get("teamID"));
        String passcode = payload.get("passcode");
        Password newPassword = new Password(teamID, passcode);
        passwordService.savePassword(newPassword);
    }

    @DeleteMapping("/{teamID}")
    public void deletePassword(@PathVariable Integer teamID) {
        passwordService.deletePasswordByTeamID(teamID);
    }
}
