package edu.aitu.oop3.repository.jdbc;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    public void save(Car car) {
        String sql = """
            INSERT INTO cars (plate, brand, model, year, daily_price, status, available)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();

            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, car.getPlate());
                ps.setString(2, car.getBrand());
                ps.setString(3, car.getModel());
                ps.setInt(4, car.getYear());
                ps.setBigDecimal(5, car.getDailyPrice());
                ps.setString(6, car.getStatus());
                ps.setBoolean(7, car.isAvailable());

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        car.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving car", e);
        }
    }

    public List<Car> findAll() {
        String sql = "SELECT id, plate, brand, model, year, daily_price, status, available FROM cars ORDER BY id";
        List<Car> cars = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    cars.add(new Car(
                            rs.getInt("id"),
                            rs.getString("plate"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getBigDecimal("daily_price"),
                            rs.getString("status"),
                            rs.getBoolean("available")
                    ));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error reading cars", e);
        }

        return cars;
    }

    public Car findById(int id) {
        String sql = "SELECT id, plate, brand, model, year, daily_price, status, available FROM cars WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new Car(
                                rs.getInt("id"),
                                rs.getString("plate"),
                                rs.getString("brand"),
                                rs.getString("model"),
                                rs.getInt("year"),
                                rs.getBigDecimal("daily_price"),
                                rs.getString("status"),
                                rs.getBoolean("available")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding car by id", e);
        }

        return null;
    }

    public void setAvailability(int carId, boolean available) {
        String sql = "UPDATE cars SET available = ? WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setBoolean(1, available);
                ps.setInt(2, carId);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating car availability", e);
        }
    }
}
