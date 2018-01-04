package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;
import model.Task;
import service.TaskService;
import service.impl.TaskServiceImpl;
import start.AppManager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminTasksTableController implements Initializable {

    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, Integer> clmID;
    @FXML
    private TableColumn<Task, Integer> clmStudentID;
    @FXML
    private TableColumn<Task, String> clmTask;
    @FXML
    private TableColumn<Task, String> clmAnswer;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExit;

    @Getter
    private static Task selectedTask;
    @Getter
    private static Task deleteTask;
    @Getter
    private static Stage updateStage;
    @Getter
    private static Stage deleteStage;
    @Getter
    private static Stage addStage;
    @Getter
    private static Stage exitStage;
    @Getter
    private static Stage distanceUpdateStage;

    @Getter
    @Setter
    private static List<Task> distanceList;

    private ObservableList<Task> tasksList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        btnAdd.setOnAction(this::add);
        btnDelete.setOnAction(this::delete);
        btnExit.setOnAction(this::exit);
        btnUpdate.setOnAction(this::update);

        taskTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectedTask = taskTable.getSelectionModel().getSelectedItem();
                Parent parent = null;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/UpdateWindow.fxml"));
                try {
                    parent = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                updateStage = new Stage();
                Scene scene = new Scene(parent);
                parent.getStylesheets().add("css/btn.css");
                updateStage.setScene(scene);
                updateStage.initModality(Modality.WINDOW_MODAL);
                Window window = ((Node) event.getSource()).getScene().getWindow();
                updateStage.initOwner(window);
                updateStage.show();
                updateStage.setOnHiding(e -> load());
            }
        });
    }

    /**
     * Open new modal window for load all data to distance database
     */
    private void update(ActionEvent event) {
        Parent parent = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/SaveToDistanceBase.fxml"));
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        distanceUpdateStage = new Stage();
        Scene scene = new Scene(parent);
        parent.getStylesheets().add("css/css.btn");
        distanceUpdateStage.setScene(scene);
        distanceUpdateStage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        distanceUpdateStage.initOwner(window);
        distanceUpdateStage.show();
    }

    /**
     * Open new modal window for Exit programme, without save all changes
     */
    private void exit(ActionEvent event) {
        Parent parent = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/ExitWindow.fxml"));
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exitStage = new Stage();
        Scene scene = new Scene(parent);
        parent.getStylesheets().add("css/css.btn");
        exitStage.setScene(scene);
        exitStage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        exitStage.initOwner(window);
        exitStage.show();
    }

    /**
     * Open new modal window for delete line of table where your cursor
     */
    private void delete(ActionEvent event) {
        deleteTask = taskTable.getSelectionModel().getSelectedItem();
        Parent parent = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/DeleteWindow.fxml"));
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteStage = new Stage();
        Scene scene = new Scene(parent);
        parent.getStylesheets().add("css/btn.css");
        deleteStage.setScene(scene);
        deleteStage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        deleteStage.initOwner(window);
        deleteStage.show();

        deleteStage.setOnHiding(e -> load());
    }

    /**
     * Open new modal window where will enter new task
     */
    private void add(ActionEvent event) {
        Parent parent = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/AddTask.fxml"));
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addStage = new Stage();
        Scene scene = new Scene(parent);
        parent.getStylesheets().add("css/btn.css");
        addStage.setScene(scene);
        addStage.initModality(Modality.WINDOW_MODAL);
        Window window = ((Node) event.getSource()).getScene().getWindow();
        addStage.initOwner(window);
        addStage.show();
        addStage.setOnHiding(e -> load());
    }

    /**
     * Initialise all columns of task class and load all tasks in local table
     */
    private void init() {
        clmID.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        clmTask.setCellValueFactory(new PropertyValueFactory<>("task"));
        clmAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        try {
            clearLocalDatabase(new TaskServiceImpl().loadLocalTasks());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadFromDistanceBase();
        load();
    }

    /**
     * For clear your local database
     */
    private void clearLocalDatabase(List<Task> list) {
        TaskService taskService = new TaskServiceImpl();
        try {
            taskService.clearLocalDatabase(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect to distance database and load all tasks
     */
    private void loadFromDistanceBase() {
        TaskService taskService = new TaskServiceImpl();
        distanceList = AppManager.getAllTasks();
        for (Task task : distanceList) {
            try {
                taskService.save(task);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Load all tasks from local database
     */
    private void load() {
        tasksList.clear();
        List<Task> list = null;
        TaskService taskService = new TaskServiceImpl();
        try {
            list = taskService.loadLocalTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tasksList.addAll(list);
        taskTable.setItems(tasksList);
    }
}