package com.f1management.model;

import jakarta.persistence.*;

@Entity
@Table(name="participated")
public class Participated {

    @EmbeddedId
    private ParticipatedId id;

    @ManyToOne
    @MapsId("carId")
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @MapsId("raceId")
    @JoinColumn(name = "race_id")
    private Race race;

    public ParticipatedId getId() {
        return id;
    }

    public void setId(ParticipatedId part_id) {
        this.id = part_id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
