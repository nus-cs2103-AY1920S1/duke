package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    /**
     * Constructor.
     *
     * @param stage JavaFXStage
     */
    public void start(Stage stage) {
        AnchorPane ap = new MainWindow(duke);
        Scene scene = new Scene(ap);
        scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

