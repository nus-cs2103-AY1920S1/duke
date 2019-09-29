import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * This is class that represents controller for MainWindow. It provides the layout for the other controls.
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

    /**
     * Initializes the layout of the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setPadding(new Insets(10, 10, 10, 10));
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets <code>Duke</code> object to an existing reference.
     *
     * @param duke a <code>Duke</code> object that will help process the user's command
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.setUp(), dukeImage)
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
        if (input.equals("bye")) {
            exit();
        }
        userInput.clear();
    }

    /**
     * Exits the chat box after showing the goodbye message.
     */
    //@@author ZhangHuafan
    //Reused from https://github.com/jyx11011/duke/blob/master/src/main/java/duke/ui/MainWindow.java
    public void exit() {
        final int exitingDelay = 2;
        userInput.setDisable(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(exitingDelay));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
    //@@author
}