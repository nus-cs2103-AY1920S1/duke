package duke.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Duke GUI application.
 */
public final class DukeGui extends Application {
    /**
     * Starts the Duke GUI application.
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setMinHeight(900.0);
            stage.setMinWidth(600.0);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
