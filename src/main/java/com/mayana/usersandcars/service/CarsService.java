package com.mayana.usersandcars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayana.usersandcars.entity.Cars;
import com.mayana.usersandcars.repository.CarsRepository;


@Service
public class CarsService {

	@Autowired
    private CarsRepository carsRepository;

    public List<Cars> getAllCars() {
        return (List<Cars>) carsRepository.findAll();
    }

    public Cars getCarById(Long id) {
        return carsRepository.findById(id).orElse(null);
    }

    public Cars createCar(Cars cars) {
        return carsRepository.save(cars);
    }

    public Cars updateCar(Long id, Cars updatedCar) {
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

    public void deleteCar(Long id) {
        carsRepository.deleteById(id);
    }
}