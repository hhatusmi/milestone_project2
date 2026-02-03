package edu.aitu.oop3.ui;
import edu.aitu.oop3.model.Car;
import edu.aitu.oop3.repository.jdbc.CarRepository;
import java.math.BigDecimal;
public class Main {
    public static void main(String[] args) {
        CarRepository repo = new CarRepository();
        Car car = new Car(
                "KZ-123-ABC",
                "Toyota",
                "Camry",
                2020,
                new BigDecimal("15000.00"),
                "AVAILABLE",
                true
        );
        repo.save(car);
        System.out.println("Saved car id = " + car.getId());
        repo.findAll().forEach(c ->
                System.out.println(c.getId() + " " + c.getPlate() + " " + c.getBrand() + " " + c.getModel() + " " + c.getDailyPrice())
        );
    }
}
