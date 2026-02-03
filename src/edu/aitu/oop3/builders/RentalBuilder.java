package edu.aitu.oop3.builders;

import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.entities.Customer;
import edu.aitu.oop3.entities.Rental;

import java.time.LocalDate;

public class RentalBuilder {
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    public RentalBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public RentalBuilder setCar(Car car) {
        this.car = car;
        return this;
    }

    public RentalBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public RentalBuilder setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public RentalBuilder setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Rental build() {
        if (customer == null || car == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Customer, car, start date, and end date must be provided");
        }
        return new Rental(customer, car, startDate, endDate, totalPrice);
    }
}