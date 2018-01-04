package start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Task;
import service.InitService;
import service.impl.TaskServiceImpl;

import java.util.List;

public class AppManager extends Application {

    @Getter
    @Setter
    private static int myId;

    @Getter
    private static Stage stage;

    @Getter
    @Setter
    private static List<Task> allTasks = new TaskServiceImpl().loadAllTasks();

    @Override
    public void start(Stage primaryStage) throws Exception {
        InitService.createTableWithTasks();
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/TitleWindow.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/btn.css");
        primaryStage.getIcons().add(new Image("/photo/miracle.png"));
        primaryStage.setScene(scene);
        primaryStage.maxHeightProperty().setValue(450);
        primaryStage.minHeightProperty().setValue(450);
        primaryStage.maxWidthProperty().setValue(600);
        primaryStage.minWidthProperty().setValue(600);
        primaryStage.setTitle("COMPANY TASKS MANAGER");
        primaryStage.show();
    }
}