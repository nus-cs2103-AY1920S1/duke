package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
            stage.getIcons().add(new Image("/images/DaDuke.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            // Lock Dimensions of stage
            stage.setMinHeight(stage.getHeight());
            stage.setMinWidth(stage.getWidth());
            stage.setMaxHeight(stage.getHeight());
            stage.setMaxWidth(stage.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
