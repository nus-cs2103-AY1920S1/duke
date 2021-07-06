import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
    
    private Image dukeNeutral = new Image(this.getClass().getResourceAsStream("/images/DukeNeutral.png"));
    private Image dukePout = new Image(this.getClass().getResourceAsStream("/images/DukePout.png"));
    private Image dukeSmile = new Image(this.getClass().getResourceAsStream("/images/DukeSmile.png"));
    private Image dukeSweat = new Image(this.getClass().getResourceAsStream("/images/DukeSweat.png"));
    private Image dukeWink = new Image(this.getClass().getResourceAsStream("/images/DukeWink.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);

        dialogContainer.setSpacing(10);

        // Anchor scrollPane
        AnchorPane.setTopAnchor(scrollPane, 0d);
        AnchorPane.setBottomAnchor(scrollPane, 45d);
        AnchorPane.setLeftAnchor(scrollPane, 0d);
        AnchorPane.setRightAnchor(scrollPane, 0d);

        // Anchor userInput
        AnchorPane.setBottomAnchor(userInput, 0d);
        AnchorPane.setLeftAnchor(userInput, 0d);
        AnchorPane.setRightAnchor(userInput, 80d);

        // Anchor sendButton
        AnchorPane.setBottomAnchor(sendButton, 0d);
        AnchorPane.setRightAnchor(sendButton, 0d);
    }

    /**
     * Sets the reference to Duke.
     * 
     * @param d The instance of Duke whose reference is to be passed to the MainWindow
     */
    public void setDuke(Duke d) {
        duke = d;

        //Makes Duke say hi
        String dukeGreetingMessage = duke.sayHi();
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog(dukeGreetingMessage, dukeNeutral)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * 
     * @throws FileNotFoundException If a save file does not exist and cannot be created, or cannot be opened.
     * @throws IOException When an IOException occurs.
     * @throws SecurityException If a security manager exists and its checkWrite method denies 
     *     write access to the save file.
     */
    @FXML
    private void handleUserInput() throws FileNotFoundException, IOException, SecurityException {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        Image dukeImageInstance = chooseDukeImage();

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImageInstance)
        );
        userInput.clear();

        if (duke.systemShouldShutdown()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        }
    }

    /**
     * Causes the button to glow when moused over.
     */
    @FXML
    private void onMouseEnterButton() {
        Glow glow = new Glow();
        glow.setLevel(0.5);
        sendButton.setEffect(glow);
    }

    /**
     * Causes the button to stop glowing when moused over.
     */
    @FXML
    private void onMouseExitButton() {
        Glow glow = new Glow();
        glow.setLevel(0);
        sendButton.setEffect(glow);
    }

    /**
     * Returns the image of Duke to be displayed.
     * 
     * @return The image of Duke to be displayed.
     */
    private Image chooseDukeImage() {
        switch (GlobalDukeImageChoiceBuffer.getDukeImageChoice()) {
        case Neutral:
            return dukeNeutral;
            //Fallthrough
        case Pout:
            return dukePout;
            //Fallthrough
        case Smile:
            return dukeSmile;
            //Fallthrough
        case Sweat:
            return dukeSweat;
            //Fallthrough
        case Wink:
            return dukeWink;
            //Fallthrough
        default:
            return dukeNeutral;
            //Fallthrough
        }
    }
}
