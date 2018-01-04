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
import java.util.ResourceBundle;

public class DeleteWindowController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    @FXML
    private Text txtDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Task del = AdminTasksTableController.getDeleteTask();
        String txt = "Do you want delete task " + del.getId() + " for student " + del.getStudentID();
        txtDelete.setText(txt);
        btnCancel.setOnAction(event -> cancel());
        btnDelete.setOnAction(event -> delete());
    }

    /**
     * Delete chosen task and close modal window
     */
    private void delete() {
        int id = AdminTasksTableController.getDeleteTask().getId();
        TaskService taskService = new TaskServiceImpl();
        try {
            taskService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AdminTasksTableController.getDeleteStage().close();
    }

    /**
     * Cancel and close modal window
     */
    private void cancel() {
        AdminTasksTableController.getDeleteStage().close();
    }
}