package edu.aitu.oop3.services;

import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.entities.Customer;
import edu.aitu.oop3.entities.Rental;
import edu.aitu.oop3.repository.RentalRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalService {

    private final RentalRepository rentalRepository = new RentalRepository();

    public Rental createRental(Customer customer, Car car, LocalDate startDate, LocalDate endDate) throws SQLException {
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double totalPrice = days * car.getPricePerDay();

        Rental rental = new Rental(customer, car, startDate, endDate, totalPrice);
        return rentalRepository.create(rental);
    }
}