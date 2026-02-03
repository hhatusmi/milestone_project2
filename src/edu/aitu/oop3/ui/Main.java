package edu.aitu.oop3.ui;

import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.entities.Customer;
import edu.aitu.oop3.entities.Rental;
import edu.aitu.oop3.repository.CarRepository;
import edu.aitu.oop3.repository.CustomerRepository;
import edu.aitu.oop3.repository.RentalRepository;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CustomerRepository customerRepo = new CustomerRepository();
        CarRepository carRepo = new CarRepository();
        RentalRepository rentalRepo = new RentalRepository();

        try {
            Customer c = new Customer("Alice", "alice@mail.com", "123456789");
            customerRepo.create(c);
            System.out.println("Customer added: " + c);

            Car car = new Car("Toyota", "Camry", 2022, 50.0, "Sedan");
            carRepo.create(car);
            System.out.println("Car added: " + car);

            Rental rental = new Rental(c, car, LocalDate.now(), LocalDate.now().plusDays(3), 150.0);
            rentalRepo.create(rental);
            System.out.println("Rental added: " + rental);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

