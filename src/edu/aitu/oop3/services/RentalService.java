package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.entities.Rental;
import edu.aitu.oop3.repository.RentalRepository;

import java.time.LocalDate;
import java.util.List;
public class RentalService {
    private final RentalRepository rentalRepository = new RentalRepository();
    private final CarService carService = new CarService();
    public Rental createRental(int carId, String customerName, LocalDate start, LocalDate end) {
        Car car = carService.getCarById(carId);
        if (car == null) throw new IllegalArgumentException("Car not found: " + carId);
        if (!car.isAvailable()) throw new IllegalStateException("Car is not available");
        Rental rental = Rental.builder()
                .carId(carId)
                .customerName(customerName)
                .startDate(start)
                .endDate(end)
                .build();
        rentalRepository.save(rental);
        carService.setAvailability(carId, false);
        return rental;
    }
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    public List<Rental> findRentalsByCustomer(String customer) {
        return rentalRepository.findAll()
                .stream()
                .filter(r -> r.getCustomerName().equalsIgnoreCase(customer))
                .toList();
    }
}
