package edu.aitu.oop3.repository;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements Repository<Payment> {

    private final DatabaseConnection db = DatabaseConnection.getInstance();

    @Override
    public Payment create(Payment payment) throws SQLException {
        String sql = """
                INSERT INTO payments (rental_id, amount, paid_at)
                VALUES (?, ?, ?)
                RETURNING id
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, payment.getRentalId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setTimestamp(3, Timestamp.valueOf(payment.getPaidAt()));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) payment.setId(rs.getLong("id"));
            }
        }
        return payment;
    }

    @Override
    public Payment findById(int id) throws SQLException {
        String sql = "SELECT * FROM payments WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapPayment(rs);
            }
        }
        return null;
    }

    @Override
    public List<Payment> findAll() throws SQLException {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payments";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) list.add(mapPayment(rs));
        }
        return list;
    }

    @Override
    public void update(Payment payment) throws SQLException {
        String sql = """
                UPDATE payments
                SET rental_id = ?, amount = ?, paid_at = ?
                WHERE id = ?
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, payment.getRentalId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setTimestamp(3, Timestamp.valueOf(payment.getPaidAt()));
            stmt.setLong(4, payment.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM payments WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void save(Payment payment) throws SQLException {
        if (payment.getId() == 0) create(payment);
        else update(payment);
    }

    private Payment mapPayment(ResultSet rs) throws SQLException {
        Payment p = new Payment();
        p.setId(rs.getLong("id"));
        p.setRentalId(rs.getLong("rental_id"));
        p.setAmount(rs.getDouble("amount"));
        p.setPaidAt(rs.getTimestamp("paid_at").toLocalDateTime());
        return p;
    }
}