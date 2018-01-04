package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleController implements Initializable {

    @Getter
    private static Stage modalStage;

    @FXML
    private Text txtCompany;
    @FXML
    private Text txtTasks;
    @FXML
    private Text txtManager;
    @FXML
    private Button btnReg;
    @FXML
    private Button btnModer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnReg.setOnAction(this::registration);
        btnModer.setOnAction(this::login);
    }

    /**
     * Open modal window for login
     */
    private void login(ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/LoginWindow.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modalStage = new Stage();
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("/css/btn.css");
        modalStage.getIcons().add(new Image("/photo/miracle.png"));
        modalStage.setScene(scene);
        modalStage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        modalStage.initOwner(window);
        modalStage.setTitle("Enter your log/pass:");
        modalStage.show();
    }

    /**
     * Open modal registration window
     */
    private void registration(javafx.event.ActionEvent event) {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/RegistrWindow.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        modalStage = new Stage();
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("/css/btn.css");
        modalStage.getIcons().add(new Image("/photo/miracle.png"));
        modalStage.setScene(scene);
        modalStage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        modalStage.initOwner(window);
        modalStage.setTitle("Registration new Student:");
        modalStage.show();
    }
}