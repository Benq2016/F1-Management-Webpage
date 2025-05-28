package com.f1management.controller;

import com.f1management.model.Car;
import com.f1management.model.Driver;
import com.f1management.model.Mechanic;
import com.f1management.model.Team;
import com.f1management.service.CarService;
import com.f1management.service.DriverService;
import com.f1management.service.MechanicService;
import com.f1management.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private MechanicService mechanicService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public Car addCar(@RequestBody Map<String, Object> data) {
        Integer id = (Integer) data.get("id");
        String model = (String) data.get("model");
        Integer driverID = (Integer) data.get("driverID");
        Integer mechanicID = (Integer) data.get("mechanicID");
        Integer teamID = (Integer) data.get("teamID");

        System.out.println(id + model + driverID + mechanicID + teamID);

        Driver driver = driverService.getDriverById(driverID).orElse(null);
        Mechanic mechanic = mechanicService.getMechanicById(mechanicID).orElse(null);
        Team team = teamService.getTeamById(teamID).orElse(null);

        Car car = new Car(id, model, driver, mechanic, team);
        return carService.saveCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Integer id, @RequestBody Map<String, Object> data) {
        String model = (String) data.get("model");
        return carService.updateCar(id, model);
    }
}
