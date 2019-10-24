package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//Some design for MainWindow.fxml adapted from https://github.com/ang-zeyu/duke/blob/master/src/main/java/duke/ui/MainWindow.java

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
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets duke and displays welcome message to user.
     */
    public void setDuke(Duke d) {
        duke = d;
        displayWelcomeMessage();
    }

    public void displayWelcomeMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getWelcomeDukeDialog("HELLO FROM DUKE!! \n"
                                + duke.ui.printList(duke.tasks.getList()) + "\n\nInput 'help' for user guide."
                        , dukeImage, duke.tasks.getDoneTaskCount(), duke.tasks.getTotalTaskCount()));
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