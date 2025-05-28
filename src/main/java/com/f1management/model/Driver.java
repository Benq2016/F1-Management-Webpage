package com.f1management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="driver")
public class Driver {
    @Id
    private Integer id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Car> cars;

    public Driver(Integer id, String name, int age, Team team) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public Driver() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer driver_id) {
        this.id = driver_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String toString(){
        return "ID: " + id + " Name: " + name + " Age: " + age +  " Team: " + team;
    }
}
