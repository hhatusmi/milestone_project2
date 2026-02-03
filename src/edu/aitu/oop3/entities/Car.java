package edu.aitu.oop3.entities;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private double pricePerDay;
    private String type;

    private Car(CarBuilder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.model = builder.model;
        this.year = builder.year;
        this.pricePerDay = builder.pricePerDay;
        this.type = builder.type;
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPricePerDay() { return pricePerDay; }
    public String getType() { return type; }

    @Override
    public String toString() {
        return "Car{id=" + id + ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", pricePerDay=" + pricePerDay +
                ", type='" + type + '\'' + '}';
    }

    public static class CarBuilder {
        private int id;
        private String brand;
        private String model;
        private int year;
        private double pricePerDay;
        private String type;

        public CarBuilder setId(int id) { this.id = id; return this; }
        public CarBuilder setBrand(String brand) { this.brand = brand; return this; }
        public CarBuilder setModel(String model) { this.model = model; return this; }
        public CarBuilder setYear(int year) { this.year = year; return this; }
        public CarBuilder setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; return this; }
        public CarBuilder setType(String type) { this.type = type; return this; }

        public Car build() {
            return new Car(this);
        }
    }
}
