package com.f1management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="team")
public class Team {
    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Driver> drivers;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mechanic> mechanics;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Car> cars;

    public Integer getId() {
        return id;
    }

    public void setId(Integer team_id) {
        this.id = team_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Mechanic> getMechanics() {
        return mechanics;
    }

    public void setMechanics(List<Mechanic> mechanics) {
        this.mechanics = mechanics;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String toString(){
        return "ID: " + id + " Name: " + name;
    }
}
