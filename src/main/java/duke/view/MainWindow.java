package duke.view;
import duke.main.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private HBox logoBox;
    @FXML
    private HBox dialogBox;
    @FXML
    private HBox inputBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    @FXML
    private ImageView logoPicture;
    private Image logoImage = new Image(this.getClass().getResourceAsStream("/images/logo.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setContent(dialogContainer);
//        Image image = new Image(this.getClass().getResourceAsStream("/images/background.jpg"));
//        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
//        Background background = new Background(backgroundImage);
//        dialogContainer.setBackground(background);
        dialogContainer.setId("dialogContainer");
        logoBox.setStyle("-fx-background-color: #2196F3");
        logoPicture.setImage(logoImage);

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}