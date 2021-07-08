package duke.gui;

import duke.Duke;
import duke.gui.controllers.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 * Refer to https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart4.md
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            MainWindow mainWindow = new MainWindow(duke);
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(mainWindow);
            AnchorPane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
