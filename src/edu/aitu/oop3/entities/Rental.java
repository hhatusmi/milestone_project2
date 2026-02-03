package edu.aitu.oop3.entities;

import java.time.LocalDate;

public class Rental implements Identifiable {

    private long id;
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;

    public Rental() {}

    public Rental(Customer customer, Car car, LocalDate startDate, LocalDate endDate, double totalPrice) {
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    @Override
    public long getId() { return id; }

    @Override
    public void setId(long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    @Override
    public String toString() {
        return "Rental{id=" + id + ", customerId=" + (customer != null ? customer.getId() : null) +
                ", carId=" + (car != null ? car.getId() : null) +
                ", start=" + startDate + ", end=" + endDate + ", total=" + totalPrice + "}";
    }
}