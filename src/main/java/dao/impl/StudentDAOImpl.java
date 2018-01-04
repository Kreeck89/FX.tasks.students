package dao.impl;

import dao.StudentDAO;
import data.Database;
import model.Student;
import start.AppManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {

    private static final String INSERT_INTO = "INSERT INTO tasks.students (name, surname, email, password, about) VALUES (?,?,?,?,?)";
    private static final String GET_STUDENT_ID = "SELECT * FROM tasks.Students WHERE email = ? AND password = ?";
    private static final String GET_ROLE_ID = "SELECT * FROM tasks.roles WHERE studentID = ?";
    private static final String GET_ROLE = "SELECT * FROM tasks.role WHERE id = ?";

    @Override
    public void registrNewStudent(Student student) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setString(5, student.getAbout());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkDoubleEmail(String email) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT email FROM tasks.students")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("email").equals(email)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getStudentRole(String log, String pas) {
        int studentID = 0;
        int roleID = 0;
        String role = "";

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ID)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, log);
            preparedStatement.setString(2, pas);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentID = resultSet.getInt("id");
                AppManager.setMyId(studentID);
            }
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(GET_ROLE_ID)) {
                preparedStatement1.setInt(1, studentID);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                    roleID = resultSet1.getInt("roleID");
                }
            }
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(GET_ROLE)) {
                preparedStatement1.setInt(1, roleID);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                    role = resultSet1.getString("role");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}