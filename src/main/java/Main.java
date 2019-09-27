import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import slave.exception.DukeException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static Stage stage;
    private Duke duke = new Duke();

    public Main() throws DukeException, IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            Main.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Kappa");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}