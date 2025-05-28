package com.f1management.model;

import jakarta.persistence.*;

@Entity
@Table(name="password")
public class Password {
    @Id
    private Integer team_id;
    private String passcode;

    public Password(Integer id, String password) {
        this.team_id = id;
        this.passcode = password;
    }

    public Password() {}

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer id) {
        this.team_id = id;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
