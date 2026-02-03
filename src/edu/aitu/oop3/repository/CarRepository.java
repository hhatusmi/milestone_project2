package edu.aitu.oop3.repository;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements Repository<Car> {

    private final DatabaseConnection db = DatabaseConnection.getInstance();

    @Override
    public Car create(Car car) throws SQLException {
        String sql = """
                INSERT INTO cars (brand, model, year, price_per_day, type)
                VALUES (?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPricePerDay());
            stmt.setString(5, car.getType());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                car.setId(rs.getInt("id"));
            }
        }
        return car;
    }

    @Override
    public Car findById(int id) throws SQLException {
        String sql = "SELECT * FROM cars WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapCar(rs);
            }
        }
        return null;
    }

    @Override
    public List<Car> findAll() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cars.add(mapCar(rs));
            }
        }
        return cars;
    }

    @Override
    public void update(Car car) throws SQLException {
        String sql = """
                UPDATE cars
                SET brand = ?, model = ?, year = ?, price_per_day = ?, type = ?
                WHERE id = ?
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPricePerDay());
            stmt.setString(5, car.getType());
            stmt.setInt(6, car.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void save(Car car) throws SQLException {
        if (car.getId() == 0) {
            create(car);
        } else {
            update(car);
        }
    }

    private Car mapCar(ResultSet rs) throws SQLException {
        return new Car(
                rs.getInt("id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getInt("year"),
                rs.getDouble("price_per_day"),
                rs.getString("type")
        );
    }
}

