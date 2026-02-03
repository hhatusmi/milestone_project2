package edu.aitu.oop3.repository;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements Repository<Payment> {

    private final Connection connection;

    public PaymentRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Payment create(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (rental_id, amount, payment_date, status) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, payment.getRentalId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.setString(4, payment.getStatus());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                payment.setId(rs.getInt("id"));
            }
        }
        return payment;
    }

    @Override
    public Payment findById(int id) throws SQLException {
        String sql = "SELECT * FROM payments WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payment(
                        rs.getInt("id"),
                        rs.getInt("rental_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date").toLocalDate(),
                        rs.getString("status")
                );
            }
        }
        return null;
    }

    @Override
    public void save(Payment item) {

    }

    @Override
    public List<Payment> findAll() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("id"),
                        rs.getInt("rental_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date").toLocalDate(),
                        rs.getString("status")
                ));
            }
        }
        return payments;
    }

    @Override
    public void update(Payment payment) throws SQLException {
        String sql = "UPDATE payments SET rental_id = ?, amount = ?, payment_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, payment.getRentalId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.setString(4, payment.getStatus());
            stmt.setInt(5, payment.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM payments WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

