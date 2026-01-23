package edu.aitu.oop3.repository.jdbc;
import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.model.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CarRepository {
    public void save(Car car) {
        String sql = """
            INSERT INTO cars (brand, model, year, available)
            VALUES (?, ?, ?, ?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getYear());
            ps.setBoolean(4, car.isAvailable());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    car.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving car", e);
        }
    }
    public List<Car> findAll() {
        String sql = "SELECT id, brand, model, year, available FROM cars ORDER BY id";
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Car car = new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getBoolean("available")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading cars", e);
        }
        return cars;
    }
}

