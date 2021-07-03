import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Rori rori;

    private boolean isInTutorial;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRori(Rori rori) {
        this.rori = rori;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rori's reply 
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox userDialog = DialogBox.getUserDialog(input);
        DialogBox roriDialog;
        String response;
        if (isInTutorial) {
            response = rori.getTutorialResponse(input);
            isInTutorial = false;
            roriDialog = DialogBox.getRoriNormalDialog(response);
        } else {
            try {
                response = rori.getResponse(input);
                roriDialog = DialogBox.getRoriNormalDialog(response);
            } catch (RoriException error) {
                response = error.getMessage();
                roriDialog = DialogBox.getRoriErrorDialog(response);
            }
        }
        dialogContainer.getChildren().addAll(
                userDialog,
                roriDialog
        );
        userInput.clear();
    }

    /**
     * Prints the welcome message for the user.
     */
    public void welcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getRoriNormalDialog(this.rori.welcomeMessage())
        );
    }

    /**
     * Prints to ask if the user wants a tutorial on their initial startup of the program.
     */
    public void tutorialMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getRoriNormalDialog(this.rori.getTutorial())
        );
        isInTutorial = true;
    }
}