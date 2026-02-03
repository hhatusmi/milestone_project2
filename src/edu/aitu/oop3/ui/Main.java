package edu.aitu.oop3.ui;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.entities.Customer;
import edu.aitu.oop3.entities.Rental;
import edu.aitu.oop3.factories.CarFactory;
import edu.aitu.oop3.repository.CarRepository;
import edu.aitu.oop3.repository.CustomerRepository;
import edu.aitu.oop3.repository.RentalRepository;
import edu.aitu.oop3.services.PaymentService;
import edu.aitu.oop3.services.RentalService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        CustomerRepository customerRepo = new CustomerRepository();
        CarRepository carRepo = new CarRepository();
        RentalService rentalService = new RentalService();
        PaymentService paymentService = new PaymentService();

        try {
            DatabaseConnection.getInstance().getConnection().close();
            System.out.println(" Connected to database");
            Customer c = new Customer(
                    "Amir",
                    "+77001234567",
                    "amir@mail.com"
            );
            customerRepo.create(c);
            System.out.println("Customer added: " + c);

            Car car = CarFactory.createCar(
                    "Sedan",
                    "Toyota",
                    "Camry",
                    2022,
                    15000.0
            );
            carRepo.create(car);
            System.out.println("Car added: " + car);

            Rental rental = rentalService.createRental(
                    c,
                    car,
                    LocalDate.now(),
                    LocalDate.now().plusDays(3)
            );
            System.out.println("Rental added: " + rental);


            var payment = paymentService.processPayment(
                    rental.getId(),
                    rental.getTotalPrice()
            );
            System.out.println("Payment added: " + payment);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}