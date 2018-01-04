package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.StudentService;
import service.impl.StudentServiceImpl;
import start.AppManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable {

    @FXML
    private Button btnRegistr;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnRegistr.setOnAction(this::goToRegistrationPage);
        btnLogin.setOnAction(this::loadTableWithTasks);
    }

    /**
     * Check email and password in distance database and if true - open Table with tasks;
     * If false - open WRONG modal window
     */
    private void loadTableWithTasks(ActionEvent event) {
        String log = txtLogin.getText();
        String pas = txtPass.getText();
        StudentService service = new StudentServiceImpl();
        String studentRole = service.getStudentRole(log, pas);

        if (studentRole.equals("ADMIN")) {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/AdminTasksWindow.fxml")));
                scene.getStylesheets().add("css/btn.css");
                AppManager.getStage().setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
            TitleController.getModalStage().close();
        } else {
            String fxml = "/view/NoAdmin.fxml";
            String title = "WRONG";
            Stage stage = createModalityWindow(fxml, title, event);
            stage.show();
        }
    }

    /**
     * Open registration modal window
     */
    private void goToRegistrationPage(ActionEvent event) {
        String fxml = "/view/RegistrWindow.fxml";
        String title = "Registration new Student:";
        Stage stage = createModalityWindow(fxml, title, event);
        stage.show();
    }


    private Stage createModalityWindow(String fxml, String title, ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxml));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Stage stage = new Stage();
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("css/btn.css");
        stage.getIcons().add(new Image("/photo/miracle.png"));
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        stage.initOwner(window);
        stage.setTitle(title);
        return stage;
    }
}