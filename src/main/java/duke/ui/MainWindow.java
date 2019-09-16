package duke.ui;

import duke.command.CommandResult;
import duke.command.CommandType;
import duke.handler.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class representing the main window of the app.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));
    private Duke duke;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
    }

    /**
     * Initializes the main window with duke handler.
     * @param duke the duke handler to handle request.
     */
    public void init(Duke duke) {
        this.duke = duke;
        try {
            duke.loadTasks();
        } catch (FileNotFoundException e) {
            addDukeDialogBox(Ui.showLoadingError(e));
        } finally {
            addDukeDialogBox(Ui.greet());
        }
    }

    /**
     * Adds a duke dialog box to main window.
     * @param text the text to be shown in the dialog box.
     */
    public void addDukeDialogBox(String text) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(text, dukeImage));
    }

    /**
     * Adds a user dialog box to main window.
     * @param text the text to be shown in the dialog box.
     */
    public void addUserDialogBox(String text) {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(text, userImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        try {
            addUserDialogBox(input);
            CommandResult commandResult = duke.execute(input);
            addDukeDialogBox(duke.getResultUi(commandResult));
            if (commandResult.getCommandType() == CommandType.Exit) {
                try {
                    duke.storeTasks();
                } catch (IOException e) {
                    addDukeDialogBox(duke.getStoringErrorUi(e));
                }
                exit();
            }
        } catch (Exception e) {
            addDukeDialogBox(duke.getParsingErrorUi(e));
        } finally {
            userInput.clear();
        }
    }

    /**
     * Exits the application after 3 seconds.
     */
    public void exit() {
        userInput.setDisable(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}