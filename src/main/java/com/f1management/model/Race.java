package com.f1management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="race")
public class Race {
    @Id
    private Integer id;
    private String name;
    private String location;
    private LocalDateTime date;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participated> participants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer race_id) {
        this.id = race_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Participated> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participated> participants) {
        this.participants = participants;
    }

    public String getPrintedDate() {
        return date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
    }
}