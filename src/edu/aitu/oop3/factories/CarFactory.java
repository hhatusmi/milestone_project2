package edu.aitu.oop3.factories;

import edu.aitu.oop3.entities.Car;

public class CarFactory {

    public static Car createCar(String type, String brand, String model, int year, double pricePerDay) {
        switch (type.toLowerCase()) {
            case "sedan":
                return new Car.CarBuilder()
                        .setBrand(brand)
                        .setModel(model)
                        .setYear(year)
                        .setPricePerDay(pricePerDay)
                        .setType("Sedan")
                        .build();
            case "suv":
                return new Car.CarBuilder()
                        .setBrand(brand)
                        .setModel(model)
                        .setYear(year)
                        .setPricePerDay(pricePerDay)
                        .setType("SUV")
                        .build();
            case "truck":
                return new Car.CarBuilder()
                        .setBrand(brand)
                        .setModel(model)
                        .setYear(year)
                        .setPricePerDay(pricePerDay)
                        .setType("Truck")
                        .build();
            default:
                throw new IllegalArgumentException("Unknown car type: " + type);
        }
    }
}
