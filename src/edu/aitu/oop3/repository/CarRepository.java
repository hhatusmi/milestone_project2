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
                INSERT INTO cars (plate, brand, model, year, available, price_per_day, type)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getPlate());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setBoolean(5, car.isAvailable());
            stmt.setDouble(6, car.getPricePerDay());
            stmt.setString(7, car.getType());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    car.setId(rs.getLong("id"));   // ✅ long
                }
            }
        }
        return car;
    }

    @Override
    public Car findById(int id) throws SQLException {
        String sql = "SELECT * FROM cars WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id); // ✅ можно и setInt, но long универсальнее

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapCar(rs);
                }
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
                SET plate = ?, brand = ?, model = ?, year = ?, available = ?, price_per_day = ?, type = ?
                WHERE id = ?
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getPlate());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYear());
            stmt.setBoolean(5, car.isAvailable());
            stmt.setDouble(6, car.getPricePerDay());
            stmt.setString(7, car.getType());
            stmt.setLong(8, car.getId()); // ✅ long

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
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
        Car car = new Car();

        car.setId(rs.getLong("id"));
        car.setPlate(rs.getString("plate"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getInt("year"));
        car.setAvailable(rs.getBoolean("available"));
        car.setPricePerDay(rs.getDouble("price_per_day"));
        car.setType(rs.getString("type"));

        return car;
    }
}