package service.impl;

import dao.TaskDAO;
import dao.impl.TaskDAOImpl;
import model.Task;
import service.TaskService;

import java.sql.SQLException;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    @Override
    public int save(Task task) throws SQLException {
        TaskDAO taskDAO = new TaskDAOImpl();
        return taskDAO.save(task);
    }

    @Override
    public int update(Task task) throws SQLException {
        TaskDAO taskDAO = new TaskDAOImpl();
        return taskDAO.update(task);
    }

    @Override
    public void delete(int id) throws SQLException {
        TaskDAO taskDAO = new TaskDAOImpl();
        taskDAO.delete(id);
    }

    @Override
    public void clearLocalDatabase(List<Task> list) throws SQLException {
        TaskDAO taskDAO = new TaskDAOImpl();
        taskDAO.clearLocalDatabase(list);
    }

    @Override
    public List<Task> loadLocalTasks() throws SQLException {
        TaskDAO taskDAO = new TaskDAOImpl();
        return taskDAO.loadLocalTasks();
    }

    @Override
    public List<Task> loadAllTasks() {
        TaskDAO taskDAO = new TaskDAOImpl();
        return taskDAO.loadAllTasks();
    }

    @Override
    public void saveAllTasksToDistanceBase(List<Task> allTasks) {
        TaskDAO taskDAO = new TaskDAOImpl();
        taskDAO.saveAllTasksToDistanceBase(allTasks);
    }
}