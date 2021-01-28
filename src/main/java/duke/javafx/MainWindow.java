package duke.javafx;

import duke.Duke;
import duke.dukeexception.DukeException;
import duke.ui.Response;
import duke.ui.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static javafx.application.Platform.exit;

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

    private Ui ui;
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        ui = new Ui();
        duke = new Duke();
        String firstGreeting;

        if(duke.getLastOpenedFile() == null) {
            firstGreeting = ui.printLogoAndGreet()
                    + "\n" + ui.printMetaDataCorrupted()
                    + "\n" + ui.printRequestForFilePath();
        } else {
            firstGreeting = ui.printLogoAndGreet()
                    + "\n" + ui.printLastOpenedFilePath(duke.getLastOpenedFile())
                    + "\n" + ui.printRequestForFilePath();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(firstGreeting, dukeImage)
        );
        userInput.setText("data/");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }
    public void setDukeUI(Ui ui) {
        this.ui = ui;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.getResponse(input);
        String responseMessage = response.getMessage();


        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(responseMessage, dukeImage)
        );

        userInput.clear();

        if (response.isExitResponse()) {
            exit();
        }
    }
}