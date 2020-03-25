package com.koro.carshomework7.controller;

import com.koro.carshomework7.dao.CarsDao;
import com.koro.carshomework7.exception.CarNotFoundException;
import com.koro.carshomework7.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CarController {

    private CarsDao carsDao;

    @Autowired
    public CarController(CarsDao carsDao) {
        this.carsDao = carsDao;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Car>> getCars() {
        List<Car> carList = carsDao.getCarList();
        carList.forEach(car -> car.add(linkTo(CarController.class).slash(car.getId()).withSelfRel()));
        Link link = linkTo(CarController.class).withSelfRel();
        CollectionModel<Car> carCollection = new CollectionModel<>(carList, link);
        return new ResponseEntity<>(carCollection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Car>> getCarById(@PathVariable int id) {
        Car carById =  carsDao.getCarById(id).orElseThrow(() -> new CarNotFoundException(id));
        Link link = linkTo(CarController.class).slash(id).withSelfRel();
        EntityModel<Car> carEntity = new EntityModel<>(carById, link);
        return new ResponseEntity<>(carEntity, HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<CollectionModel<Car>> getCarsByColor(@PathVariable String color) {
        List<Car> carList = carsDao.getCarsByColor(color);
        carList.forEach(car -> car.add(linkTo(CarController.class).slash(car.getId()).withSelfRel()));
        carList.forEach(car -> car.add(linkTo(CarController.class).withRel("allColors")));
        Link link = linkTo(CarController.class).withSelfRel();
        CollectionModel<Car> carCollection = new CollectionModel<>(carList, link);
        return new ResponseEntity(carCollection, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addCar(@RequestBody Car newCar) {
        carsDao.addCar(newCar);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity modifyCar(@PathVariable long id, @RequestBody Car newCar) {
        carsDao.modifyCar(id, newCar);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity removeCar(@PathVariable int id) {
        carsDao.removeCar(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
