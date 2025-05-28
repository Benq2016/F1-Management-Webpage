package com.f1management.model;

import jakarta.persistence.*;

@Entity
@Table(name="car")
public class Car {
    @Id
    private Integer id;
    private String model;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "mechanic_id",referencedColumnName = "id", nullable = false)
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    public Car(Integer id, String model, Driver driver, Mechanic mechanic, Team team) {
        this.id = id;
        this.model = model;
        this.driver = driver;
        this.mechanic = mechanic;
        this.team = team;
    }

    public Car() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer car_id) {
        this.id = car_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String toString() {
        return "ID: " + id + " Model: " + model + " Driver: " + driver + " Mechanic: " + mechanic;
    }
}
