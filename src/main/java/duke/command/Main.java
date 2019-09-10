package duke.command;

import java.io.IOException;

import duke.Duke;
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
            Scene scene = loadLayout(stage);
            addWelcomeMessage(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Scene loadLayout(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        stage.show();
        return scene;
    }

    private void addWelcomeMessage(Scene scene) {
        String welcomeMessage  = "Hello! I'm duke.Duke\nWhat can I do for you?";
        VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage,
                        new Image(this.getClass().getResourceAsStream("/images/DaDuke.png")))
        );
    }
}

