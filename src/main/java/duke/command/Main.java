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

    /**
     * Set up the FXML
     * @param stage Stage which the fxml file is used
     * @return a Scene
     * @throws IOException
     */
    private Scene loadLayout(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        stage.show();
        return scene;
    }

    /**
     * Prints the welcome message
     * @param scene the Scene at which the welcome message is displayed
     */
    private void addWelcomeMessage(Scene scene) {
        String welcomeMessage  = "Hello! My name is Missy Wathev Udan.\nWhat can I do for you?";
        VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage,
                        new Image(this.getClass().getResourceAsStream("/images/DaDuke.png")))
        );
    }
}

