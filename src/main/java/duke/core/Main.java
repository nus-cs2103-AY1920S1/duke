package duke.core;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for duke.core.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDuke(duke);
        mainWindow.setHiMsg();
        stage.setScene(new Scene(mainWindow));
        stage.setTitle("Duke");
        stage.show();
    }
}