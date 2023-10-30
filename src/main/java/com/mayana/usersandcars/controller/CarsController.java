package com.mayana.usersandcars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayana.usersandcars.entity.Cars;
import com.mayana.usersandcars.repository.CarsRepository;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
    @Autowired
    private CarsRepository carsRepository;

    @GetMapping
    public List<Cars> getAllCars() {
        return (List<Cars>) carsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cars getCarById(@PathVariable Long id) {
        return carsRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Cars createCar(@RequestBody Cars cars) {
        return carsRepository.save(cars);
    }

    @PutMapping("/{id}")
    public Cars updateCar(@PathVariable Long id, @RequestBody Cars updatedCar) {
        Cars existingCar = carsRepository.findById(id).orElse(null);
        if (existingCar != null) {
            existingCar.setYear(updatedCar.getYear());
            existingCar.setLicensePlate(updatedCar.getLicensePlate());
            existingCar.setModel(updatedCar.getModel());
            existingCar.setColor(updatedCar.getColor());

            return carsRepository.save(existingCar);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carsRepository.deleteById(id);
    }
    
    
}
