package duke.core;

import duke.ui.DialogBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class DukeController {

    private static final String URL_DUKE_IMAGE = "/images/DaDuke.png";
    private static final String URL_USER_IMAGE = "/images/DaUser.png";
    private static final String URL_MAIN_VIEW  = "/views/duke.fxml";

    @FXML private TextField userInput;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;

    private Image userImage;
    private Image dukeImage;

    private DukeModel model;
    private Scene scene;

    /**
     * Creates new instance of DukeController.
     * Use this method instead of the constructor to create
     * a new instance of {@link DukeController}. This method
     * would help to initialize a new root node, and return
     * a controller that is attached to this node.
     *
     * @param model instance of {@link DukeModel}
     * @return controller attached to the root node
     */
    public static DukeController createInstance(DukeModel model) throws IOException {
        URL mainViewUrl = DukeController.class.getResource(DukeController.URL_MAIN_VIEW);
        FXMLLoader fxmlLoader = new FXMLLoader(mainViewUrl);
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        DukeController controller = fxmlLoader.getController();

        controller.initialize(model, scene);
        model.initialize(controller);

        return controller;
    }

    public Scene getScene() {
        return this.scene;
    }

    public void addDukeDialog(String message) {
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, this.dukeImage));
    }

    public void addUserDialog(String message) {
        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(message, this.userImage));
    }

    private void initialize(DukeModel dukeModel, Scene scene) {
        this.model = dukeModel;
        this.scene = scene;

        this.dukeImage = new Image(this.getClass().getResourceAsStream(DukeController.URL_DUKE_IMAGE));
        this.userImage = new Image(this.getClass().getResourceAsStream(DukeController.URL_USER_IMAGE));

        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        this.model.getResponse(this.userInput.getText());
        this.userInput.clear();
    }

}