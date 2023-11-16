package com.mayana.usersandcars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mayana.usersandcars.entity.Cars;
import com.mayana.usersandcars.repository.CarsRepository;
import com.mayana.usersandcars.service.CarsService;

@SpringBootTest
@ActiveProfiles("testCarsService")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CarsServiceTest {
	
	@Autowired
    private CarsService carsService;

    @Autowired
    private CarsRepository carsRepository;

    @Test
    public void testGetAllCars() {
        Cars car1 = new Cars();
        Cars car2 = new Cars();
        when(carsRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        List<Cars> carList = carsService.getAllCars();

        assertEquals(2, carList.size());
    }

    @Test
    public void testGetCarById() {
        Cars car = new Cars();
        when(carsRepository.findById(1L)).thenReturn(Optional.of(car));

        Cars retrievedCar = carsService.getCarById(1L);

        assertNotNull(retrievedCar);
    }

    @Test
    public void testCreateCar() {
        Cars car = new Cars();
        when(carsRepository.save(any(Cars.class))).thenReturn(car);

        Cars createdCar = carsService.createCar(car);

        assertNotNull(createdCar);
    }

    @Test
    public void testUpdateCar() {
        Cars existingCar = new Cars();
        existingCar.setId(1L);
        when(carsRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        when(carsRepository.save(any(Cars.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cars updatedCar = new Cars();
        updatedCar.setYear(2022);

        Cars result = carsService.updateCar(1L, updatedCar);

        assertNotNull(result);
        assertEquals(2022, result.getYear());
    }

    @Test
    public void testDeleteCar() {
        Cars car = new Cars();
        car.setId(1L);
        when(carsRepository.findById(1L)).thenReturn(Optional.of(car));

        carsService.deleteCar(1L);

        verify(carsRepository, times(1)).deleteById(1L);
    }
}
