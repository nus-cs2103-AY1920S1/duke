package duke.frontend;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    // Constructs a Duke.
    String dir = "./dukeData.txt";
    Duke duke = new Duke(dir);

    /**
     * Stages the main window after initialisation.
     *
     * @param stage stage to be shown in the main window.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke Bot");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            fxmlLoader.<MainWindow>getController().showDukeIntro();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
