import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A GUI for Era using FXML.
 */
public class Main extends Application {

    private Era duke = new Era("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap, Color.STEELBLUE);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("ERA -- Easily Remember All");
            stage.setResizable(false);
            stage.show();
            fxmlLoader.<MainWindow>getController().welcomeMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}