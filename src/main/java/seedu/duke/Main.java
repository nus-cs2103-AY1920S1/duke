package seedu.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seedu.duke.controllers.MainWindow;
import seedu.duke.storage.Storage;

import java.io.IOException;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke); // To attach duke to the MainWindow
            stage.show();
            fxmlLoader.<MainWindow>getController().startDuke();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            Storage.getInstance().saveToDisk(duke.getTasks());
        } catch (Storage.StorageOperationException e) {
            e.printStackTrace();
        }
    }
}
