package com.f1management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="mechanic")
public class Mechanic {
    @Id
    private Integer id;
    private String name;
    private int salary;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "mechanic", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Car> cars;

    public Mechanic(Integer id, String name, int salary, Team team) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.team = team;
    }

    public Mechanic() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer mechanic_id) {
        this.id = mechanic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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
        return "ID: " + id + " Name: " + name + " Age: " + salary +  " Team: " + team;
    }
}
