package dukegui;

import duke.exception.DukeIoException;
import dukegui.controller.MainWindow;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private DukeGui duke;

    public Main() throws DukeIoException {
        duke = new DukeGui();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap, Color.LIGHTBLUE);

            MainWindow window = fxmlLoader.getController();
            window.setDuke(duke);

            stage.setScene(scene);
            stage.setTitle("DUKE");
            stage.setMinHeight(350.0);
            stage.setMinWidth(450.0);
            stage.show();

            window.greetUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
