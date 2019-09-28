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
    String filePath = "data/dukeData.txt";
    private Duke duke = new Duke(filePath);

    @Override
    public void start(Stage stage) {
        assert filePath == null || filePath == "" : "filePath should not be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Duke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}