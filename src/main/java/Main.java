import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * A GUI for Task Chick using FXML.
 */
public class Main extends Application {

    private TaskChick taskChick = new TaskChick();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskChick(taskChick);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.setTitle("Task Chick - Chicky to keep you on track!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}