package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:postgresql://ec2-54-217-235-166.eu-west-1.compute.amazonaws.com:5432/dft57ump7h08qo?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private static final String USERNAME = "pmijhwmlzxigpe";
    private static final String PASSWORD = "29495ebffd2a9daffa035c035a6381a203129eb1192bef10f709f490e192bb0a";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}