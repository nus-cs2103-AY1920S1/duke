
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
    private static String FILE_PATH = "tasks.txt";
    private static String FXML_PATH = "/view/MainWindow.fxml";
    private static String TITLE = "Duke";


    private Duke duke = new Duke(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_PATH));
            assert fxmlLoader != null : "fxmlLoader is null!";
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(TITLE);
            fxmlLoader.<MainWindow>getController().initialize();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}