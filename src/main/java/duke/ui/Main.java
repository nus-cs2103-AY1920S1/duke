package duke.ui;

import duke.Duke;
import duke.DukeException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("John");

            DukeException.mw = fxmlLoader.<MainWindow>getController();
            duke.onUiReady();
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
