package duke;

import javafx.application.Application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow userInterface = fxmlLoader.getController();
            userInterface.setDuke(duke);
            stage.setTitle("Comic Duke");
            userInterface.dukeGreet();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}