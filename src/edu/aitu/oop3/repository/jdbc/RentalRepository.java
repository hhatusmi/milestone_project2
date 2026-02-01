package edu.aitu.oop3.repository.jdbc;
import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.model.Rental;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalRepository {
    public void save(Rental rental) {

        String sql = "INSERT INTO rentals (car_id, customer_name, start_date, end_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rental.getCarId());
            ps.setString(2, rental.getCustomerName());
            ps.setDate(3, Date.valueOf(rental.getStartDate()));
            ps.setDate(4, Date.valueOf(rental.getEndDate()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rental.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving rental", e);
        }
    }
    public List<Rental> findAll() {
        String sql = "SELECT id, car_id, customer_name, start_date, end_date FROM rentals ORDER BY id";
        List<Rental> rentals = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int carId = rs.getInt("car_id");
                String customerName = rs.getString("customer_name");
                LocalDate startDate = rs.getDate("start_date").toLocalDate();
                LocalDate endDate = rs.getDate("end_date").toLocalDate();
                rentals.add(new Rental(id, carId, customerName, startDate, endDate));
            }
            return rentals;
        } catch (SQLException e) {
            throw new RuntimeException("Error reading rentals", e);
        }
    }

    public class rentalRepository implements Repository<Rental> {

        private List<Rental> rentals = new ArrayList<>();

        @Override
        public void save(Rental rental) {
            rentals.add(rental);
            System.out.println("Saving rental for customer: " + rental.getCustomerName());
        }

        @Override
        public List<Rental> findAll() {
            System.out.println("Returning all rentals");
            return rentals;
        }

        @Override
        public Optional<Rental> findById(int id) {
            return rentals.stream()
                    .filter(r -> r.getId() == id)
                    .findFirst();
        }

        @Override
        public void delete(int id) {
            rentals.removeIf(r -> r.getId() == id);
            System.out.println("Deleted rental with id: " + id);
        }
    }
}
