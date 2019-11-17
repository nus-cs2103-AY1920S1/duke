package duke.view;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {
    /**
     * This is the box used to contain the duke logo.
     */
    @FXML
    private HBox logoBox;
    /**
     * This is the logo picture inside the logo box.
     */
    @FXML
    private ImageView logoPicture;
    /**
     * This is the image of the logo. It is contained inside the logo picture.
     */
    private Image logoImage = new Image(this.getClass().getResourceAsStream("/images/logo.png"));
    /**
     * This is the dialog box used to contain individual dialogs.
     */
    @FXML
    private HBox dialogBox;
    /**
     * This is the scroll pane used to scroll up and down to look through multiple dialogs.
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * This is the box used to contain the scroll pane.
     */
    @FXML
    private VBox scrollBox;
    /**
     * This is the box used to contain the text field and send button.
     */
    @FXML
    private HBox inputBox;
    /**
     * This is the text field where the user input a command.
     */
    @FXML
    private TextField userInput;
    /**
     * This is the send button to send the user input to duke.
     */
    @FXML
    private Button sendButton;

    /**
     * Initialize the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(scrollBox.heightProperty());
        scrollPane.setContent(scrollBox);
        scrollBox.setId("scrollBox");
        scrollBox.getChildren().add(DialogBox.showWelcome());
        logoBox.setId("logoBox");
        logoPicture.setImage(logoImage);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. If <code>input == "bye"</code>, the duke program
     * will exit.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        scrollBox.getChildren().addAll(DialogBox.getUserDialog(input), DialogBox.getDukeDialog(input));
        userInput.clear();
        if (isExit(input)) {
            exitMainWindow();
        }
    }

    /**
     * Exits the main window and closes the application when called.
     */
    private void exitMainWindow() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
        //@@author bjhoohaha-reused
        //Used to fix issue where exit message is not displayed when exit
        //Reused from https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx with minor modifications
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            Platform.exit();
            System.exit(0);
        });
        //@@author
        delay.play();
    }

    /**
     * Checks if the user input is a {@link duke.command.ExitCommand} that tells the program to exit.
     * @param input the user input
     * @return true if the user input is the bye command, false otherwise.
     */
    private boolean isExit(String input) {
        return input.equals("bye");
    }

}
