package duke;

import duke.ui.MainWindow;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for the Duke application using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts the Duke application.
     *
     * @param stage JavaFX stage to use for GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Snowball the Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
