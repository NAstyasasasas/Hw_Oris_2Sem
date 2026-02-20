package utils;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataClass {
    public static Connection getConn() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hw_dao", "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
