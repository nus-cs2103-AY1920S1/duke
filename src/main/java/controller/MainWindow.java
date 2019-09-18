package controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Duke;

/**
 * Controller class for MainWindow. Provides the layout for the other controls.
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Prints a welcome message by Duke. Used in main.Main when Duke is started up.
     */
    public void printWelcomeMessage() {
        String response = duke.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container. Clears the
     * user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText() + "\n";
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                new Group(DialogBox.getUserDialog(input, userImage)),
                new Group(DialogBox.getDukeDialog(response, dukeImage))
        );
        userInput.clear();
        if (response.equals(Duke.EXIT_MESSAGE)) {
            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}