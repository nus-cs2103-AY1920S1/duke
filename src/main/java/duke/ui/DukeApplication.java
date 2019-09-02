package duke.ui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A GUI for Duke using FXML.
 * Handles the initialisation logic for Duke's user interface.
 */
public class DukeApplication extends Application {

    /** Duke instance of this javafx duke application. */
    private Duke duke;

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
            stage.initStyle(StageStyle.UNDECORATED);
            duke = new Duke(fxmlLoader.<MainWindow>getController());
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().exitHandler =
                    (ActionEvent event) -> stage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}