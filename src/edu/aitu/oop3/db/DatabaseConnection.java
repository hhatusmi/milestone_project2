package edu.aitu.oop3.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://aws-1-ap-southeast-2.pooler.supabase.com:5432/postgres?sslmode=require";

    // ВАЖНО: формат postgres.<project-ref>
    private static final String USER = "postgres.ftmenecmibzocczrpnec";

    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new RuntimeException("DB_PASSWORD is not set in Run Configuration environment variables");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
