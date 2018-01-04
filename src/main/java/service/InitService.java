package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitService {

    public static void createTableWithTasks() {
        String URL = "jdbc:sqlite:miracle.sqlite";
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS \"tasks\" (\"id\" INTEGER PRIMARY KEY  NOT NULL ,\"studentID\" INTEGER NOT NULL ,\"task\" TEXT DEFAULT (null) ,\"answer\" TEXT DEFAULT (null))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}