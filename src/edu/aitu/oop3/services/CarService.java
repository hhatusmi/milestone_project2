package edu.aitu.oop3.services;
import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.repository.CarRepository;
import java.util.List;
public class CarService {
    private final CarRepository carRepository = new CarRepository();
    public void addCar(Car car) {
        carRepository.save(car);
    }
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    public Car getCarById(int id) {
        return carRepository.findById(id);
    }
    public void setAvailability(int carId, boolean available) {
        carRepository.setAvailability(carId, available);
    }
}
