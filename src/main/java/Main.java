import duke.handler.Duke;
import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

/**
 * A class representing the javafx application.
 */
public class Main extends Application {
    private static final String FILEPATH = Path.of(System.getProperty("user.dir"),
            "data", "duke.txt").toString();
    private Duke duke = new Duke(FILEPATH);

    /**
     * The main entry point for JavaFX applications.
     * @param stage the stage to set set scene.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setTitle("Duke");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    duke.storeTasks();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fxmlLoader.<MainWindow>getController().init(duke);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
