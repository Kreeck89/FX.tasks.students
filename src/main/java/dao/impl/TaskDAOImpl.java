package dao.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import dao.TaskDAO;
import data.Database;
import model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    private static final String DELETE_TASKS = "DELETE FROM tasks.all_tasks WHERE id != 0";
    private static final String INSERT_TASKS = "INSERT INTO tasks.all_tasks (id, studentID, task, answer) VALUES (?,?,?,?)";
    private static final String URL_TASKS = "SELECT * FROM tasks.all_tasks";
    private static final String URL = "jdbc:sqlite:miracle.sqlite";
    private Dao<Task, Integer> dao;

    {
     try {
         ConnectionSource source = new JdbcConnectionSource(URL);
         dao = DaoManager.createDao(source, Task.class);
     } catch (SQLException e) {
         e.printStackTrace();
     }
    }

    @Override
    public int save(Task task) throws SQLException {
        return dao.create(task);
    }

    @Override
    public int update(Task task) throws SQLException {
        return dao.update(task);
    }

    @Override
    public void delete(int id) throws SQLException {
        dao.deleteById(id);
    }

    @Override
    public void clearLocalDatabase(List<Task> list) throws SQLException {
        dao.delete(list);
    }

    @Override
    public List<Task> loadLocalTasks() throws SQLException {
        return dao.queryForAll();
    }

    @Override
    public List<Task> loadAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        try (Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(URL_TASKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setStudentID(resultSet.getInt("studentID"));
                task.setTask(resultSet.getString("task"));
                task.setAnswer(resultSet.getString("answer"));
                allTasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTasks;
    }

    @Override
    public void saveAllTasksToDistanceBase(List<Task> allTasks) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TASKS);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASKS)) {
            statement.execute();
            connection.setAutoCommit(false);
            for (Task task : allTasks) {
                preparedStatement.setInt(1, task.getId());
                preparedStatement.setInt(2, task.getStudentID());
                preparedStatement.setString(3, task.getTask());
                preparedStatement.setString(4, task.getAnswer());
                preparedStatement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}