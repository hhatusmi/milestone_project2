package edu.aitu.oop3.repository;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Car;
import edu.aitu.oop3.entities.Customer;
import edu.aitu.oop3.entities.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository implements Repository<Rental> {

    private final DatabaseConnection db = DatabaseConnection.getInstance();

    @Override
    public Rental create(Rental rental) throws SQLException {
        String sql = """
                INSERT INTO rentals (customer_id, car_id, start_date, end_date, total_price)
                VALUES (?, ?, ?, ?, ?)
                RETURNING id
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, rental.getCustomer().getId());
            stmt.setLong(2, rental.getCar().getId());
            stmt.setDate(3, Date.valueOf(rental.getStartDate()));
            stmt.setDate(4, Date.valueOf(rental.getEndDate()));
            stmt.setDouble(5, rental.getTotalPrice());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) rental.setId(rs.getLong("id"));
            }
        }
        return rental;
    }

    @Override
    public Rental findById(int id) throws SQLException {
        String sql = "SELECT * FROM rentals WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRental(rs);
            }
        }
        return null;
    }

    @Override
    public List<Rental> findAll() throws SQLException {
        List<Rental> list = new ArrayList<>();
        String sql = "SELECT * FROM rentals";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) list.add(mapRental(rs));
        }
        return list;
    }

    @Override
    public void update(Rental rental) throws SQLException {
        String sql = """
                UPDATE rentals
                SET customer_id = ?, car_id = ?, start_date = ?, end_date = ?, total_price = ?
                WHERE id = ?
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, rental.getCustomer().getId());
            stmt.setLong(2, rental.getCar().getId());
            stmt.setDate(3, Date.valueOf(rental.getStartDate()));
            stmt.setDate(4, Date.valueOf(rental.getEndDate()));
            stmt.setDouble(5, rental.getTotalPrice());
            stmt.setLong(6, rental.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM rentals WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void save(Rental rental) throws SQLException {
        if (rental.getId() == 0) create(rental);
        else update(rental);
    }

    private Rental mapRental(ResultSet rs) throws SQLException {
        Rental r = new Rental();
        r.setId(rs.getLong("id"));

        Customer c = new Customer();
        c.setId(rs.getLong("customer_id"));
        r.setCustomer(c);

        Car car = new Car();
        car.setId(rs.getLong("car_id"));
        r.setCar(car);

        r.setStartDate(rs.getDate("start_date").toLocalDate());
        r.setEndDate(rs.getDate("end_date").toLocalDate());
        r.setTotalPrice(rs.getDouble("total_price"));
        return r;
    }
}