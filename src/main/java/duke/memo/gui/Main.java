package duke.memo.gui;

import java.io.IOException;

import duke.memo.Duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            Image icon = new Image(this.getClass().getResourceAsStream("/images/shoebill-icon.jpg"));
            stage.getIcons().add(icon);
            stage.setTitle("Duke");

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showHelloMsg();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}