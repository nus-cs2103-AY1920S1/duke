package duke.gui;

import duke.Duke;
import duke.gui.controllers.MainWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(duke);
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }
}
