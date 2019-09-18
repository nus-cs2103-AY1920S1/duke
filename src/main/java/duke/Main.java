package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("メイドちゃん - Maid Chan");
            MainWindow window = fxmlLoader.getController();
            duke = new Duke("data/tasks.txt", window);
            window.setDuke(duke);
            duke.init();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
