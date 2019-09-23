package seedu.duke.core;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import seedu.duke.ui.MainWindow;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Main entry point for GUI.
     *
     * @param stage JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Obot Wan Kenobi");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            duke = new Duke("\\src\\main\\resources\\data\\tasks.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}