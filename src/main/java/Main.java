import java.io.IOException;
import core.Duke;
import exceptions.DukeException;
import ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Core.Duke using FXML.
 */
public class Main extends Application {

    /**
     * Starts the UI for Core.Duke.
     */
    @Override
    public void start(Stage stage) throws DukeException, IOException {
        Duke duke = new Duke();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setVariables(duke, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}