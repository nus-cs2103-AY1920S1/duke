package duke;

import java.io.IOException;

import duke.logic.Ui;
import duke.ui.DialogBox;
import duke.ui.MainWindow;
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

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            scene.getStylesheets().add("/view/Theme.css");
            stage.setScene(scene);
            stage.setTitle("PikaTodo");
            Image icon = new Image("/images/masterball.png");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show(); // set visibility to true

            // Display intro message
            VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
            Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(Ui.printWelcome(), dukeImage)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
