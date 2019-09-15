package duke;

import duke.parser.IncorrectFileFormatException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;

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
    private Duke duke;
    private Image userImage;
    private Image dukeImage;
    private Image jigglypuff;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/images/robot.png"));
        jigglypuff = new Image(this.getClass().getResourceAsStream("/images/avatar.png"));
    }

    public void setDuke(Duke d) {
        duke = d;
        String loadingErrorMessage = "";
        boolean isErrorOccurred = false;

        // Perform loading of tasks from disk
        try {
            duke.performDukeStartup();
        } catch (InvalidPathException i) {
            isErrorOccurred = true;
            loadingErrorMessage = i.getMessage();
        } catch (IncorrectFileFormatException f) {
            loadingErrorMessage = f.getMessage();
        } catch (NullPointerException n) {
            loadingErrorMessage = n.getMessage();
        } catch (FileNotFoundException z) {
            loadingErrorMessage = z.getMessage();
        }

        if (isErrorOccurred) {
            dialogContainer.getChildren().add(new DialogBox(loadingErrorMessage, dukeImage));
        }

        // Throw initial duke greeting
        dialogContainer.getChildren().add(new DialogBox(duke.getDukeWelcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.toLowerCase().startsWith("help")) {
            String remainingInput = input.toLowerCase().replace("help", "").trim();
            assert remainingInput.length() >= 0 : "Value of string length of command interested is greater than 0";

            if (remainingInput.length() == 0) {
                handleHelpPage();
            } else {
                handleHelpPage(remainingInput);
            }
        } else {
            handleOperations(input);
        }
    }

    /*
        Handles the display for help page and helpful advice for users.
     */
    private void handleHelpPage() {
        String helpLine1 = "Welcome to help page!\n";
        String helpLine2 = "Here are some commands you can use:\n";
        String helpLine3 = "list, todo, event, deadline, done, delete, find, bye";
        dialogContainer.getChildren().addAll(new HelpBox(helpLine1 + helpLine2 + helpLine3, jigglypuff));
        userInput.clear();
    }

    private void handleHelpPage(String commandInterested) {
        String helpLine1 = "Here is what I found!\n";

        boolean isByeCommand = commandInterested.toLowerCase().equals("bye");
        boolean isFindCommand = commandInterested.toLowerCase().equals("find");
        boolean isListCommand = commandInterested.toLowerCase().equals("list");
        boolean isDeleteCommand = commandInterested.toLowerCase().equals("delete");
        boolean isToDoCommand = commandInterested.toLowerCase().equals("todo");
        boolean isDeadlineCommand = commandInterested.toLowerCase().equals("deadline");
        boolean isEventCommand = commandInterested.toLowerCase().equals("event");

        String helpLine3 = "Sorry! Command not found!\n";
        if (isToDoCommand) {
            helpLine3 = commandInterested + " <task description>\n";
        } else if (isDeadlineCommand) {
            helpLine3 = commandInterested + " <task description> /by dd/mm/yyyy\n";
        } else if (isEventCommand) {
            helpLine3 = commandInterested + " <task description> /at dd/mm/yyyy\n";
        } else if (isDeleteCommand) {
            helpLine3 = commandInterested + " <task number>";
        } else if (isListCommand) {
            helpLine3 = commandInterested;
        } else if (isFindCommand) {
            helpLine3 = commandInterested + " <keyword>\n";
        } else if (isByeCommand) {
            helpLine3 = commandInterested;
        }

        dialogContainer.getChildren().addAll(new HelpBox(helpLine1 + helpLine3, jigglypuff));
        userInput.clear();
    }

    /**
        Handles usual operations other than help page.
     */
    private void handleOperations(String input) {
        String response = duke.getDukeResponse(input);

        //boolean isExit = duke.isExitRequest(input);

        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage), DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();

    }
}