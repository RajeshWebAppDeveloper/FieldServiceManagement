package com.sy.fsm.RestController;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBController {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/fsm";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
