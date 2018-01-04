package service;

import model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {

    int save(Task task) throws SQLException;

    int update(Task task) throws SQLException;

    void delete(int id) throws SQLException;

    void clearLocalDatabase(List<Task> list) throws SQLException;

    List<Task> loadLocalTasks() throws SQLException;

    List<Task> loadAllTasks();

    void saveAllTasksToDistanceBase(List<Task> allTasks);
}