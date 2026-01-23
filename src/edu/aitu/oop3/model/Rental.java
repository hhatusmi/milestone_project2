package edu.aitu.oop3.model;
import java.time.LocalDate;
public class Rental {
    public void setId(int id) {
        this.id = id;
    }
    private int id;
    private int carId;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    public Rental(int carId, String customerName, LocalDate startDate, LocalDate endDate) {
        this.carId = carId;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Rental(int id, int carId, String customerName, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.carId = carId;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getId() {
        return id;
    }
    public int getCarId() {
        return carId;
    }
    public void setCarId(int carId) {
        this.carId = carId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
