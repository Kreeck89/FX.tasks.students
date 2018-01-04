package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Task;
import service.TaskService;
import service.impl.TaskServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddWindowController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private TextArea txtAnswer;
    @FXML
    private TextArea txtTask;
    @FXML
    private TextField txtStudentID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnAction(event -> save());
    }

    /**
     * Get data from modal window, create new task and save to local database
     */
    private void save() {
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task();
        task.setStudentID(Integer.parseInt(txtStudentID.getText()));
        task.setTask(txtTask.getText());
        task.setAnswer(txtAnswer.getText());
        System.out.println(task);
        try {
            taskService.save(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AdminTasksTableController.getAddStage().close();
    }
}