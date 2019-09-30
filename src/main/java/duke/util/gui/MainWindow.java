package duke.util.gui;

import static duke.util.gui.DialogBox.getDukeDialog;
import static duke.util.gui.DialogBox.getUserDialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private GuiDuke duke;

    //private Image userImage = new Image(this.getClass().getResourceAsStream("/images/kawaii_robot.png"));
    //private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/kawaii_robot_power.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(GuiDuke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // get and display user input
        String input = userInput.getText();
        DialogBox inputDialog = getUserDialog(input);
        dialogContainer.getChildren().add(inputDialog);

        // clear user input field
        userInput.clear();

        // get and display output from Duke
        String response = duke.getResponse(input);
        DialogBox outputDialog = getDukeDialog(response);
        dialogContainer.getChildren().add(outputDialog);
    }

    public VBox getDialogContainer() {
        return this.dialogContainer;
    }
}