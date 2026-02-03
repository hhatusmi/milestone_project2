package edu.aitu.oop3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private static final String URL =
            "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.ftmenecmibzocczrpnec";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    private DatabaseConnection() throws SQLException {
        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new RuntimeException("DB_PASSWORD is not set");
        }
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

