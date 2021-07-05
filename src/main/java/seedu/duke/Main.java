package seedu.duke;

import java.io.IOException;
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

    private Image titleLogo = new Image(this.getClass().getResourceAsStream("/images/TitleLogo.png"));
    private Duke duke = new Duke();

    /**
     * Starts the GUI for Duke using FXML.
     *
     * @param stage Primary stage for the GUI
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            fxmlLoader.<MainWindow>getController().setMainScene(scene);
            stage.setTitle("DUKE BUNNY");
            stage.getIcons().add(titleLogo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}