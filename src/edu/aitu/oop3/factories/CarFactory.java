package edu.aitu.oop3.factories;

import edu.aitu.oop3.entities.Car;

public class CarFactory {

    public static Car createCar(String type, String brand, String model, int year, double pricePerDay) {
        switch (type.toLowerCase()) {
            case "sedan":
                return new Car(brand, model, year, pricePerDay, "Sedan");
            case "suv":
                return new Car(brand, model, year, pricePerDay, "SUV");
            case "truck":
                return new Car(brand, model, year, pricePerDay, "Truck");
            default:
                throw new IllegalArgumentException("Unknown car type: " + type);
        }
    }
}
