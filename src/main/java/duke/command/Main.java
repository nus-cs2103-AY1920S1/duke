package duke.command;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            scene.getStylesheets().add("/view/Style.css");
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Mr. Dukie");
            VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("Hello! I'm Mr. Dukie.\nWhat can I do for you?", dukeImage)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}