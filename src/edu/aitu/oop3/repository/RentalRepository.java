package edu.aitu.oop3.repository;

import edu.aitu.oop3.db.DatabaseConnection;
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

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRental(rs);
            }
        }
        return null;
    }

    @Override
    public List<Rental> findAll() throws SQLException {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rentals.add(mapRental(rs));
            }
        }
        return rentals;
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

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void save(Rental rental) throws SQLException {
        if (rental.getId() == 0) {
            create(rental);
        } else {
            update(rental);
        }
    }

    private Rental mapRental(ResultSet rs) throws SQLException {
        // Здесь можно потом добавить join с таблицами cars и customers
        Rental rental = new Rental();
        rental.setId(rs.getInt("id"));
        // Временно создаём Customer и Car с id
        rental.setCustomer(new edu.aitu.oop3.entities.Customer());
        rental.getCustomer().setId(rs.getInt("customer_id"));
        rental.setCar(new edu.aitu.oop3.entities.Car());
        rental.getCar().setId(rs.getInt("car_id"));
        rental.setStartDate(rs.getDate("start_date").toLocalDate());
        rental.setEndDate(rs.getDate("end_date").toLocalDate());
        rental.setTotalPrice(rs.getDouble("total_price"));
        return rental;
    }
}
