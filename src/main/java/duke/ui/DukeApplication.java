package duke.ui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * Handles the initialisation logic for Duke's user interface.
 */
public class DukeApplication extends Application {

    /** Duke instance of this javafx duke application. */
    private Duke duke;
    /** Minimum window width to be used for the stage. */
    private static final int MIN_WINDOW_WIDTH = 600;
    /** Minimum window height to be used for the stage. */
    private static final int MIN_WINDOW_HEIGHT = 300;

    /**
     * JavaFX entry method of this duke application.
     * Retrieves the mainWindow fxml file from the views folder then initialises it
     * and a Duke instance with it.
     *
     * @param stage Primary stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Duke.class.getResource("/views/MainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setMinWidth(MIN_WINDOW_WIDTH);
            stage.setMinHeight(MIN_WINDOW_HEIGHT);
            stage.setTitle("Duke");

            MainWindow dukeMainWindow = fxmlLoader.<MainWindow>getController();
            duke = new Duke(dukeMainWindow);
            dukeMainWindow.setDuke(duke);
            dukeMainWindow.exitHandler = (ActionEvent event) -> stage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}