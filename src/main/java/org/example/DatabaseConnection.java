package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;instanceName=KOSH1JO;databaseName=DoAnCNW;encrypt=true;trustServerCertificate=true";
    private static final String USERNAME = "Ackerman";
    private static final String PASSWORD = "anhtuan123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
