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

public class UpdateWindowController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private TextField txtTaskID;
    @FXML
    private TextArea txtAnswer;
    @FXML
    private TextArea txtTask;
    @FXML
    private TextField txtStudentID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtTaskID.setText(String.valueOf(AdminTasksTableController.getSelectedTask().getId()));
        txtStudentID.setText(String.valueOf(AdminTasksTableController.getSelectedTask().getStudentID()));
        txtTask.setText(AdminTasksTableController.getSelectedTask().getTask());
        txtAnswer.setText(AdminTasksTableController.getSelectedTask().getAnswer());

        btnSave.setOnAction(event -> update());
    }

    /**
     * Get data from fields of modal window and update to local database and table;
     * And close modal window
     */
    private void update() {
        Task task = new Task();
        task.setId(Integer.parseInt(txtTaskID.getText()));
        task.setStudentID(Integer.parseInt(txtStudentID.getText()));
        task.setTask(txtTask.getText());
        task.setAnswer(txtAnswer.getText());
        System.out.println(task);
        TaskService service = new TaskServiceImpl();
        try {
            service.update(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AdminTasksTableController.getUpdateStage().close();
    }
}