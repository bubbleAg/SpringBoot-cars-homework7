package com.koro.carshomework7.dao;

import com.koro.carshomework7.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarsDao {
    List<Car> getCarList();
    Optional<Car> getCarById(long id);
    List<Car> getCarsByColor(String color);
    void addCar(Car car);
    void modifyCar(long id, Car car);
    void removeCar(long id);

}
