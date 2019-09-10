import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/r2d2.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();


            VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("Hello! I'm Duke.\nWhat can I do for you?", dukeImage)
            );

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}