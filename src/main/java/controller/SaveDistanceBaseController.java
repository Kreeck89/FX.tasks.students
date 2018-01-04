package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Task;
import service.TaskService;
import service.impl.TaskServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SaveDistanceBaseController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnUpdate;
    @FXML
    private Text txt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.setOnAction(event -> updateData());
        btnCancel.setOnAction(event -> cancel());
    }

    /**
     * Get local database of tasks with change and load to the distance database,
     * abd close modal window
     */
    private void updateData() {
        List<Task> allTasks = null;
        TaskService taskService = new TaskServiceImpl();
        try {
           allTasks = taskService.loadLocalTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        taskService.saveAllTasksToDistanceBase(allTasks);
        AdminTasksTableController.getDistanceUpdateStage().close();
    }

    /**
     * Cancel without load to database with close modal window
     */
    private void cancel() {
        AdminTasksTableController.getDistanceUpdateStage().close();
    }
}