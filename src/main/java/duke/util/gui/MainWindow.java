package duke.util.gui;

import duke.util.gui.messagebox.MessageBox;
import duke.util.gui.messagebox.UserMessageBox;
import java.util.Queue;
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
    private ColourScheme colourScheme = ColourScheme.MINT;

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
        try {
            // get and display user input
            String input = userInput.getText();
            MessageBox userMessageBox = new UserMessageBox(input, this.colourScheme);
            messageBoxContainer.getChildren().add(userMessageBox);

            duke.getResponse(input);

            // clear user input field
            userInput.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // get and display output from Duke
            Queue<MessageBox> messageBoxQueue = duke.ui.getMessageBoxQueue();
            messageBoxContainer.getChildren().addAll(messageBoxQueue);
            duke.ui.getMessageBoxQueue().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
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