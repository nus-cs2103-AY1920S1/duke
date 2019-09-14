package duke.gui;

import duke.Duke;
import duke.gui.controllers.MainWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * A GUI for Duke using FXML.
 * Refer to https://github.com/nus-cs2103-AY1920S1/duke/blob/master/tutorials/javaFxTutorialPart4.md
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(duke);
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.show();
    }
}
