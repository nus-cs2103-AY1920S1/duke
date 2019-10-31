package duke.fx;

import duke.main.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 *
 * @author TeoShyanJie
 *
 */
public class Main extends Application {

    /** To instantiate Duke object. */
    private Duke duke = new Duke();

    /**
     * To start the user interface of Duke.
     * @param stage Main class.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            duke.initialise();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}