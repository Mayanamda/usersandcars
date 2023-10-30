package com.mayana.usersandcars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.mayana.usersandcars.entity.Cars;
import com.mayana.usersandcars.repository.CarsRepository;
import com.mayana.usersandcars.service.CarsService;

@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarsServiceTest {
    @Autowired
    private CarsService carsService;

    @Autowired
    private CarsRepository carsRepository;

    @Test
    public void testCreateCar() {
        Cars cars = new Cars();
        cars.setYear(2020);
        cars.setLicensePlate("XYZ123");
        cars.setModel("Sedan");
        cars.setColor("Blue");

        Cars createdCar = carsService.createCar(cars);
        assertNotNull(createdCar.getId());

        Cars retrievedCar = carsRepository.findById(createdCar.getId()).orElse(null);
        assertNotNull(retrievedCar);
        assertEquals(2020, retrievedCar.getYear());
    }

    @Test
    public void testUpdateCar() {
        Cars cars = new Cars();
        cars.setYear(2019);
        cars.setLicensePlate("ABC456");
        cars.setModel("SUV");
        cars.setColor("Red");

        Cars createdCar = carsService.createCar(cars);
        Long carId = createdCar.getId();

        Cars updatedCar = new Cars();
        updatedCar.setYear(2021);
        updatedCar.setColor("Silver");

        Cars result = carsService.updateCar(carId, updatedCar);
        assertNotNull(result);
        assertEquals(2021, result.getYear());
    }

    @Test
    public void testDeleteCar() {
        Cars cars = new Cars();
        cars.setYear(2018);
        cars.setLicensePlate("DEF789");
        cars.setModel("Convertible");
        cars.setColor("Black");

        Cars createdCar = carsService.createCar(cars);
        Long carId = createdCar.getId();

        carsService.deleteCar(carId);

        Cars deletedCar = carsRepository.findById(carId).orElse(null);
        assertNull(deletedCar);
    }
}
