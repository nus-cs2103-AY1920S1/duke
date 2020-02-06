package duke;

import java.io.IOException;

import duke.command.Ui;
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
            scene.getStylesheets().add("/view/Style.css");
            stage.setScene(scene);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

            VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
            Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(Ui.printWelcomeMessage(), dukeImage)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}