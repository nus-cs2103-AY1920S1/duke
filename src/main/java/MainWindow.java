import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

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
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Displays a welcome message when the application first starts up.
     */
    public void displayGreeting() {
        String logo = " ___     __   ____\n"
                + "|  __|  / _ \\ |  __ \\\n"
                + "| |  _  | | | | |  |  | |\n"
                + "| |_| | | |_| | |  |_| |\n"
                + "|___/  \\__/ |____/\n";
        logo += "Hello! I'm God\n" + "Here are your tasks for today:\n" + duke.getResponse("list");
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(logo, dukeImage));
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
        if (response.toLowerCase().contains("bye")) {
            exitApplication();
        }
        userInput.clear();
    }

    private void exitApplication() {
        Timer exit = new Timer();
        exit.schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 500);
    }
}