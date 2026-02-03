package edu.aitu.oop3.services;

import edu.aitu.oop3.builders.RentalBuilder;
import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.entities.Customer;
import edu.aitu.oop3.entities.Rental;
import edu.aitu.oop3.repository.RentalRepository;

import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService() {
        this.rentalRepository = new RentalRepository();
    }

    public Rental createRental(Customer customer, Car car, java.time.LocalDate startDate, java.time.LocalDate endDate) throws SQLException {
        // Рассчет totalPrice
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double totalPrice = days * car.getPricePerDay();

        Rental rental = new RentalBuilder()
                .setCustomer(customer)
                .setCar(car)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setTotalPrice(totalPrice)
                .build();

        return rentalRepository.create(rental);
    }

    public Rental findRentalById(int id) throws SQLException {
        return rentalRepository.findById(id);
    }

    public List<Rental> listRentals() throws SQLException {
        return rentalRepository.findAll();
    }

    public void updateRental(Rental rental) throws SQLException {
        rentalRepository.update(rental);
    }

    public void deleteRental(int id) throws SQLException {
        rentalRepository.delete(id);
    }
}

