package edu.aitu.oop3.entities;

public class Car implements Identifiable {

    private long id;
    private String plate;
    private String brand;
    private String model;
    private int year;
    private boolean available;
    private double pricePerDay;
    private String type;

    public Car() {}

    public Car(String plate, String brand, String model, int year, boolean available, double pricePerDay, String type) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.available = available;
        this.pricePerDay = pricePerDay;
        this.type = type;
    }

    public Car(long id, String plate, String brand, String model, int year, boolean available, double pricePerDay, String type) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.available = available;
        this.pricePerDay = pricePerDay;
        this.type = type;
    }

    @Override
    public long getId() { return id; }

    @Override
    public void setId(long id) { this.id = id; }

    public String getPlate() { return plate; }
    public void setPlate(String plate) { this.plate = plate; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "Car{id=" + id + ", plate='" + plate + "', brand='" + brand + "', model='" + model +
                "', year=" + year + ", available=" + available + ", pricePerDay=" + pricePerDay +
                ", type='" + type + "'}";
    }
}