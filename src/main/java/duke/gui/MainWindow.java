package duke.gui;

import duke.Main;
import duke.command.CommandResponse;
import duke.Duke;
import duke.exception.DukeException;
// the @FXML notation marks a private or protected member, allowing FXML to access it despite the modifier.
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaHunter.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaHandler.png"));

    /**
     * Bind the dialog container to the button of the scroll pane, and greet the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        // Greet the user
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Ui().showWelcomeMessage(duke.getTaskList()), dukeImage)
        );
    }

    /**
     * Listens to the input field and button and carries out the main business logic of Duke.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Main business logic goes here
        CommandResponse response;
        try {
            response = duke.getResponse(input);
        } catch (DukeException e) {
            response = new CommandResponse(Ui.addNewLine(e.getMessage()), false);
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
                            Ui.addNewLine(input),
                            userImage),
                    DialogBox.getDukeDialog(
                            response.getResponse(),
                            dukeImage)
            );
            // We can do this since we (1) Defined userInput in MainWindow.fxml (2) gave userInput the @FXML tag
            userInput.clear();
        }
    }
}
