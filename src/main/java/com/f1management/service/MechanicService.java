package com.f1management.service;

import com.f1management.model.Mechanic;
import com.f1management.repository.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MechanicService {

    private final MechanicRepository mechanicRepository;

    private MechanicService(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }

    public Optional<Mechanic> getMechanicById(Integer id) {
        return mechanicRepository.findById(id);
    }

    public Mechanic saveMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public void deleteMechanic(Integer id) {
        mechanicRepository.deleteById(id);
    }

    public Mechanic updateMechanic(Integer id, String name, Integer salary) {
        return mechanicRepository.findById(id)
                .map(mechanic -> {
                    mechanic.setName(name);
                    mechanic.setSalary(salary);
                    return mechanicRepository.save(mechanic);
                })
                .orElseThrow(() -> new RuntimeException("Mechanic not found with id: " + id));
    }
}
