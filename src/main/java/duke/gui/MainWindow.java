package duke.gui;

import duke.CommandResponse;
import duke.Duke;
// the @FXML notation marks a private or protected member, allowing FXML to access it despite the modifier.
import duke.Ui;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static duke.Ui.DIVIDER;
import static duke.Ui.PREFIX;

/**
 * A UI Controller class. For UI-related code.
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
        // Greet the user
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.addDividers(new Ui().showWelcomeMessage()), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Main business logic goes here
        CommandResponse response;
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            response = new CommandResponse(Ui.addPrefixNewline(e.getMessage()), false);
        }

        // Exit application programmatically if necessary
        if (response.getIsExit()) {
            // hacky way to get the primary stage
            Stage primaryStage = (Stage) scrollPane.getScene().getWindow();
            primaryStage.fireEvent(new WindowEvent(
                            primaryStage,
                            WindowEvent.WINDOW_CLOSE_REQUEST
                    ));
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(
                            Ui.addDividers(Ui.addPrefixNewline(input)),
                            userImage),
                    DialogBox.getDukeDialog(
                            Ui.addDividers(response.getResponse()),
                            dukeImage)
            );
            // We can do this since we (1) Defined userInput in MainWindow.fxml (2) gave userInput the @FXML tag
            userInput.clear();
        }
    }
}
