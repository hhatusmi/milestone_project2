package edu.aitu.oop3.db;

import java.sql.*;

public class PostgresDB implements IDB {
    private Connection connection;

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require", "postgres.ftmenecmibzocczrpnec", "DB_PASSWORD=wrwnUdAIXf4S1Sm7");
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Object entity) {
        if (entity instanceof Customer) {
            String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                Customer customer = (Customer) entity;
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getEmail());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object read(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Object entity) {
        if (entity instanceof Customer) {
            String sql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                Customer customer = (Customer) entity;
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getEmail());
                stmt.setInt(3, customer.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

