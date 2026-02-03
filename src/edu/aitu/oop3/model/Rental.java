package edu.aitu.oop3.model;
import java.time.LocalDate;
public class Rental {
    private int id;
    private int carId;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Rental() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCarId() { return carId; }
    public String getCustomerName() { return customerName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private final Rental rental = new Rental();
        public Builder carId(int carId) {
            rental.carId = carId;
            return this;
        }
        public Builder customerName(String name) {
            rental.customerName = name;
            return this;
        }
        public Builder startDate(LocalDate date) {
            rental.startDate = date;
            return this;
        }
        public Builder endDate(LocalDate date) {
            rental.endDate = date;
            return this;
        }
        public Rental build() {
            if (rental.carId <= 0) throw new IllegalArgumentException("carId must be > 0");
            if (rental.customerName == null || rental.customerName.isBlank())
                throw new IllegalArgumentException("customerName is required");
            if (rental.startDate == null || rental.endDate == null)
                throw new IllegalArgumentException("startDate and endDate are required");
            if (rental.endDate.isBefore(rental.startDate))
                throw new IllegalArgumentException("endDate cannot be before startDate");
            return rental;
        }
    }
}
