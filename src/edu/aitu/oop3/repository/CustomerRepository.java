package edu.aitu.oop3.repository;

import edu.aitu.oop3.db.DatabaseConnection;
import edu.aitu.oop3.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements Repository<Customer> {

    private final DatabaseConnection db = DatabaseConnection.getInstance();

    @Override
    public Customer create(Customer customer) throws SQLException {
        String sql = """
                INSERT INTO customers (name, email, phone_number)
                VALUES (?, ?, ?)
                RETURNING id
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhoneNumber());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer.setId(rs.getInt("id"));
            }
        }
        return customer;
    }

    @Override
    public Customer findById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapCustomer(rs);
            }
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        }
        return customers;
    }

    @Override
    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = ?, email = ?, phone_number = ? WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setInt(4, customer.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void save(Customer customer) throws SQLException {
        if (customer.getId() == 0) {
            create(customer);
        } else {
            update(customer);
        }
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone_number")
        );
    }
}

