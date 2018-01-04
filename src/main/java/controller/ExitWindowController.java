package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import start.AppManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ExitWindowController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnExit;
    @FXML
    private Text txtExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(event -> closeThisWindow());
        btnExit.setOnAction(event -> closeProgramme());
    }

    /**
     * Close modal window and programme
     */
    private void closeProgramme() {
        AdminTasksTableController.getExitStage().close();
        AppManager.getStage().close();
    }

    /**
     * Only close modal window without exit
     */
    private void closeThisWindow() {
        AdminTasksTableController.getExitStage().close();
    }
}