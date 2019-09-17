package duke.app;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    public Duke duke = new Duke();

    /**
     * Starts up the GUI.
     * @param stage The JavaFX stage being used for the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            stage.setTitle("Duke");
            AnchorPane ap = fxmlLoader.load();

            Image img = new Image(this.getClass().getResourceAsStream("/images/tentacle.png"));
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(300);
            imgView.setFitWidth(100);
            imgView.setX(500);
            imgView.setY(0);

            ap.getChildren().add(imgView);

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().welcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}