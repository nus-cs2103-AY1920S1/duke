package dose.util.gui;

import dose.util.gui.messagebox.MessageBox;
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

    private GuiDose dose;

    /** Represents the colour scheme in use for the GUI. Hardcoded as MINT for now. */
    private ColourScheme colourScheme = ColourScheme.MINT;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(messageBoxContainer.heightProperty());
        messageBoxContainer.setStyle("-fx-background-color: " + colourScheme.getBackgroundColour());
    }

    public void setDose(GuiDose d) {
        dose = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dose's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            // get and display user input
            String input = userInput.getText();
            dose.ui.showUserInput(input);

            dose.getResponse(input);

            // clear user input field
            userInput.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // get and display output from Dose
            Queue<MessageBox> messageBoxQueue = dose.ui.getMessageBoxQueue();
            messageBoxContainer.getChildren().addAll(messageBoxQueue);
            dose.ui.getMessageBoxQueue().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * Changes the appearance of Dose's GUI to match the given colour scheme.
//     * Not sure why this needs to be static. KIV implementation :(
//     * @param colourScheme Colour scheme for Dose's GUI.
//     */
//    public static void setColourScheme(ColourScheme colourScheme) {
//        this.colourScheme = colourScheme;
//    }
}