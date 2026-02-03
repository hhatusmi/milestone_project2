package edu.aitu.oop3.repository;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.entities.Customer;
import edu.aitu.oop3.entities.Rental;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository implements Repository<Rental> {

    private final Connection connection;
    private final CustomerRepository customerRepo = new CustomerRepository();
    private final CarRepository carRepo = new CarRepository();

    public RentalRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Rental create(Rental rental) throws SQLException {
        String sql = "INSERT INTO rentals (customer_id, car_id, start_date, end_date, total_price) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rental.getCustomer().getId());
            stmt.setInt(2, rental.getCar().getId());
            stmt.setDate(3, Date.valueOf(rental.getStartDate()));
            stmt.setDate(4, Date.valueOf(rental.getEndDate()));
            stmt.setDouble(5, rental.getTotalPrice());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rental.setId(rs.getInt("id"));
            }
        }
        return rental;
    }

    @Override
    public Rental findById(int id) throws SQLException {
        String sql = "SELECT * FROM rentals WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = customerRepo.findById(rs.getInt("customer_id"));
                Car car = carRepo.findById(rs.getInt("car_id"));
                return new Rental(
                        rs.getInt("id"),
                        customer,
                        car,
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getDouble("total_price")
                );
            }
        }
        return null;
    }

    @Override
    public void save(Rental item) {

    }

    @Override
    public List<Rental> findAll() throws SQLException {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Customer customer = customerRepo.findById(rs.getInt("customer_id"));
                Car car = carRepo.findById(rs.getInt("car_id"));
                rentals.add(new Rental(
                        rs.getInt("id"),
                        customer,
                        car,
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getDouble("total_price")
                ));
            }
        }
        return rentals;
    }

    @Override
    public void update(Rental rental) throws SQLException {
        String sql = "UPDATE rentals SET customer_id = ?, car_id = ?, start_date = ?, end_date = ?, total_price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rental.getCustomer().getId());
            stmt.setInt(2, rental.getCar().getId());
            stmt.setDate(3, Date.valueOf(rental.getStartDate()));
            stmt.setDate(4, Date.valueOf(rental.getEndDate()));
            stmt.setDouble(5, rental.getTotalPrice());
            stmt.setInt(6, rental.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM rentals WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

