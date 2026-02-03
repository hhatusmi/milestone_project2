package edu.aitu.oop3.factories;

import edu.aitu.oop3.entities.Car;

public class CarFactory {

    public static Car createCar(String type, String brand, String model, int year, double pricePerDay) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setPricePerDay(pricePerDay);
        car.setType(type);
        car.setAvailable(true);
        car.setPlate("KZ-" + brand.substring(0, Math.min(3, brand.length())).toUpperCase() + "-" + year);
        return car;
    }
}