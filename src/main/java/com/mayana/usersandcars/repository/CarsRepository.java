package com.mayana.usersandcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayana.usersandcars.entity.Cars;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {
}