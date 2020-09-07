package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import duke.gui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private String filePath = "data/duke.txt";
    private Duke duke = new Duke(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.getIcons().add(new Image("/images/goodmorning.png"));
            stage.setTitle("GoodMorning");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}