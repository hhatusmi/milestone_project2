package edu.aitu.oop3.repository.jdbc;
import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.model.Rental;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RentalRepository {
    public void save(Rental rental) {
        String sql = """
            INSERT INTO rentals (car_id, customer_name, start_date, end_date)
            VALUES (?, ?, ?, ?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rental.getCarId());
            ps.setString(2, rental.getCustomerName());
            ps.setDate(3, Date.valueOf(rental.getStartDate()));
            ps.setDate(4, Date.valueOf(rental.getEndDate()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) rental.setId(rs.getInt(1));
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
                Rental r = Rental.builder()
                        .carId(rs.getInt("car_id"))
                        .customerName(rs.getString("customer_name"))
                        .startDate(rs.getDate("start_date").toLocalDate())
                        .endDate(rs.getDate("end_date").toLocalDate())
                        .build();
                r.setId(rs.getInt("id"));
                rentals.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading rentals", e);
        }
        return rentals;
    }
}
