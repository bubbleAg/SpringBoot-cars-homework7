package com.koro.carshomework7.dao;

import com.koro.carshomework7.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarsDaoImpl implements CarsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> getCarList() {
        String sql = "SELECT * FROM Cars";
        List<Car> carList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Car.class));
        return carList;
    }

    @Override
    public Optional<Car> getCarById(long id) {
        String sql = "SELECT * FROM Cars WHERE id = ? ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Car.class), id).stream().findAny();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        String sql = "SELECT * FROM Cars WHERE color = ?";
        List<Car> carList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Car.class), color);
        return carList;
    }

    @Override
    public void addCar(Car car) {
        String sql = "INSERT INTO Cars (mark, model, color, production_year) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getMark(), car.getModel(), car.getColor(), car.getProductionYear());
    }

    @Override
    public void modifyCar(long id, Car car) {
        String sql = "UPDATE Cars SET mark = ?, model = ?, color = ?, production_year = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                car.getMark(),
                car.getModel(),
                car.getColor(),
                car.getProductionYear(),
                id
        );
    }

    @Override
    public void removeCar(long id) {
        String sql = "DELETE FROM Cars WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
