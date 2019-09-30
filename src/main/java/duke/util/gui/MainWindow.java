package duke.util.gui;

import static duke.util.gui.messagebox.MessageBox.getDukeMessageBox;
import static duke.util.gui.messagebox.MessageBox.getUserMessageBox;

import duke.util.gui.messagebox.MessageBox;
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
    private VBox messageBoxContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private GuiDuke duke;

    /** Represents the colour scheme in use for the GUI. Hardcoded as MINT for now. */
    private ColourScheme colourScheme = ColourScheme.GREY;

    //private Image userImage = new Image(this.getClass().getResourceAsStream("/images/kawaii_robot.png"));
    //private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/kawaii_robot_power.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(messageBoxContainer.heightProperty());
        messageBoxContainer.setStyle("-fx-background-color: " + colourScheme.getBackgroundColour());
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
        MessageBox userMessageBox = getUserMessageBox(input, this.colourScheme);
        messageBoxContainer.getChildren().add(userMessageBox);

        // clear user input field
        userInput.clear();

        // get and display output from Duke
        String response = duke.getResponse(input);
        MessageBox dukeMessageBox = getDukeMessageBox(response, this.colourScheme);
        messageBoxContainer.getChildren().add(dukeMessageBox);
    }

//    /**
//     * Changes the appearance of Duke's GUI to match the given colour scheme.
//     * Not sure why this needs to be static. KIV implementation :(
//     * @param colourScheme Colour scheme for Duke's GUI.
//     */
//    public static void setColourScheme(ColourScheme colourScheme) {
//        this.colourScheme = colourScheme;
//    }
}