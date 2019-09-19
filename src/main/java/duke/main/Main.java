package duke.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /**
     * Starts the Duke program and loads the stage with a scene.
     * @param stage the stage to set the scene on.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader mainWindowLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            BorderPane bp = mainWindowLoader.load();
            Scene scene = new Scene(bp);
            Font.loadFont(
                    Main.class.getResource("/fonts/deliusunicase-regular.ttf").toExternalForm(), 10
            );
            scene.getStylesheets().add("/style/stylesheet.css");
            stage.setMinWidth(630.0);
            stage.setMinHeight(703.0);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
