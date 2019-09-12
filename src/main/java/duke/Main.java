import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke;
    private DukeManager dukeManager;

    public Main() throws Exception {
        duke = new Duke();
        dukeManager = duke.getDukeManager();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            beginDukeMessages(fxmlLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void beginDukeMessages(FXMLLoader fxmlLoader) {
        fxmlLoader.<MainWindow>getController().welcomeMessage();
        if (this.dukeManager.getFirstTime()) {
            fxmlLoader.<MainWindow>getController().tutorialMessage();
        }
    }
}