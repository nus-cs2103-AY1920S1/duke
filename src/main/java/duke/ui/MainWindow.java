package duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for duke.main.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends Stage {
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

    public void setDuke(Duke d) {
        duke = d;
        duke.initialize(this);
    }

    /**
     * Creates the two dialog boxes, one echoing user input and the other containing duke.main.Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        duke.getResponse(input);
        userInput.clear();
    }

    // Solution below adapted from https://github.com/nexolute/duke/blob/master/src/main/java/duke/MainWindow.java
    public void printAsUser(String s) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(s, userImage));
    }

    public void printAsDuke(String s) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(s, dukeImage));
    }

}
