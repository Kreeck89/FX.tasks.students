package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentRegistrWindow implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private Button btnNext;
    @FXML
    private TextArea txtAbout;
    @FXML
    private TextField txtStudentEmail;
    @FXML
    private TextField txtStudentSurname;
    @FXML
    private TextField txtStudentName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnNext.setOnAction(this::checkDoubleStudent);
    }

    /**
     * Check to wrong email and if true - load modal window with info about it
     */
    private void checkDoubleStudent(javafx.event.ActionEvent event) {
        StudentService service = new StudentServiceImpl();
        boolean check = service.checkDoubleEmail(txtStudentEmail.getText());
        if (check == true) {
            final FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/WrongEmail.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            Window window = ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(window);
            stage.show();
        } else {
            if (!txtStudentEmail.getText().equals("") & !password.getText().equals("")) {
                Student student = new Student();
                student.setName(txtStudentName.getText());
                student.setSurname(txtStudentSurname.getText());
                student.setEmail(txtStudentEmail.getText());
                student.setPassword(password.getText());
                student.setAbout(txtAbout.getText());
                service.registryNewStudent(student);
            } else {
                return;
            }
            TitleController.getModalStage().close();
        }
    }
}