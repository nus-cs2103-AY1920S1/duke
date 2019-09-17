package seedu.duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/isabelle_neutral.png"));

    private static String expression;

    public enum Expression {
        NEUTRAL, CONFUSED, SAD, CONCERNED, SPARKLY, SHOCKED, STRESSED;
    }

    public static void setExpression(String str) {
        expression = str;
    }

    public void setImage(Expression e) {
        switch (e) {
            case NEUTRAL:
                dukeImage = new Image(this.getClass().getResourceAsStream("/images/isabelle_neutral.png"));
                break;
            case SAD:
                dukeImage = new Image(this.getClass().getResourceAsStream("/images/isabelle_sad.png"));
                break;
            case SHOCKED:
                dukeImage = new Image(this.getClass().getResourceAsStream("/images/isabelle_shocked.png"));
                break;
            case SPARKLY:
                dukeImage = new Image(this.getClass().getResourceAsStream("/images/isabelle_sparkly.png"));
                break;
            case CONFUSED:
                dukeImage = new Image(this.getClass().getResourceAsStream("/images/isabelle_confused.png"));
                break;
            case STRESSED:
                dukeImage = new Image(this.getClass().getResourceAsStream("/images/isabelle_stressed.png"));
                break;
            case CONCERNED:
                dukeImage = new Image(this.getClass().getResourceAsStream("/images/isabelle_concerned.png"));
                break;
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.intro(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
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
    }
}