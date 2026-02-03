package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.factories.CarFactory;
import edu.aitu.oop3.repository.CarRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    private final CarRepository carRepository = new CarRepository();

    public CarService() {
    }

    public Car addCar(String type, String brand, String model, int year, double pricePerDay) throws SQLException {
        Car car = CarFactory.createCar(type, brand, model, year, pricePerDay);
        return carRepository.create(car);
    }

    public Car findCarById(int id) throws SQLException {
        return carRepository.findById(id);
    }

    public List<Car> listCars() throws SQLException {
        return carRepository.findAll();
    }

    // Фильтрация машин по типу с использованием лямбд
    public List<Car> filterCarsByType(String type) throws SQLException {
        return carRepository.findAll()
                .stream()
                .filter(car -> car.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public void updateCar(Car car) throws SQLException {
        carRepository.update(car);
    }

    public void deleteCar(int id) throws SQLException {
        carRepository.delete(id);
    }
}
