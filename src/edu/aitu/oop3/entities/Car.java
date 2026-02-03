package edu.aitu.oop3.entities;
import java.math.BigDecimal;
public class Car {
    private int id;
    private String plate;
    private String brand;
    private String model;
    private int year;
    private BigDecimal dailyPrice;
    private String status;      // например: AVAILABLE
    private boolean available;
    public Car(int id, String plate, String brand, String model, int year, BigDecimal dailyPrice, String status, boolean available) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyPrice = dailyPrice;
        this.status = status;
        this.available = available;
    }
    public Car(int id, String plate, String brand, int year, boolean available) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyPrice = dailyPrice;
        this.status = status;
        this.available = available;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPlate() { return plate; }
    public void setPlate(String plate) { this.plate = plate; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public BigDecimal getDailyPrice() { return dailyPrice; }
    public void setDailyPrice(BigDecimal dailyPrice) { this.dailyPrice = dailyPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public int isAutomatic() {

        return 0;
    }
}
