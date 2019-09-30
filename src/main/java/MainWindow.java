import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private static final String EXIT_MESSAGE = "Bye! See you again :-)";

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window of the application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Background bg = getMainBackground();
        dialogContainer.setBackground(bg);
        //userInput.setStyle("-fx-text-inner-color: white;");
        greet();
    }

    private Background getMainBackground() {
        return new Background(new BackgroundFill(
                Color.web("#212121"), CornerRadii.EMPTY, new Insets(0, 0, 0, 0)));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private void greet() {
        String output = "Hello! I am Duke.";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(output, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        try {
            if (response.equalsIgnoreCase(EXIT_MESSAGE)) {
                duke.saveTasks();
                Thread.sleep(300);
                Stage stage = Stage.class.cast(VBox.class.cast(dialogContainer).getScene().getWindow());
                stage.close();
            }
        } catch (InterruptedException e) {
            System.exit(0);
        }
    }
}