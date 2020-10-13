package duke;

import java.io.IOException;

import duke.lib.common.DukeException;
import duke.lib.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {
    private Duke duke;
    private MainWindow mainWindow;

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Duke's TodoList");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setClassLoader(getClass().getClassLoader());
            fxmlLoader.setLocation(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            mainWindow = fxmlLoader.<MainWindow>getController();
            stage.show();
            run();
        } catch (IOException | DukeException e) {
            mainWindow.outputError(e);
        }
    }

    /**
     * Runs duke by checking for input and displaying errors.
     */
    public void run() throws DukeException {
        try {
            duke = new Duke();
        } finally {
            mainWindow.init(duke);
            mainWindow.showWelcome();
        }
    }
}