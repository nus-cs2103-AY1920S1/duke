import duke.handler.Duke;
import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class representing the javafx application.
 */
public class Main extends Application {
    private static final String FILEPATH = "/Users/jiangyuxin/Documents/sem1/cs2103/duke/data/duke.txt";
    private Duke duke = new Duke(FILEPATH);

    /**
     * The main entry point for JavaFX applications.
     * @param stage the stage to set set scene.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                duke.parse("bye");
            });
            fxmlLoader.<MainWindow>getController().init(duke);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
