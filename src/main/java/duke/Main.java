package duke;

import duke.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        UiManager ui = new UiManager();
        ui.start(stage);
    }
}