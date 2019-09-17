package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.DialogBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class handling pretty printing and input reading for the program.
 * Note that you do not need to create an instance of this class to print, only for reading input.
 * @author Lim Daekoon
 */
public class Ui {

    private static VBox dialogContainer;
    private static TextField userInput;
    private static Image user;
    private static Image duke;

    /**
     * Initializes the UI class.
     * Must be run once every time.
     * @param vbox vBox object that handles the dialogue.
     * @param textField TextField object that contains user's input
     * @param userImage Image object to represent user
     * @param dukeImage Image object to represent duke
     */
    public static void initialize(VBox vbox, TextField textField, Image userImage, Image dukeImage) {
        dialogContainer = vbox;
        userInput = textField;
        user = userImage;
        duke = dukeImage;
    }

    /**
     * Reads in the user's command, and create a dialog box containing user's reply.
     * Clears the user input after processing.
     * @return Parser object that parsed the line of input.
     * @throws DukeException If there is a problem with the command read in.
     */

    public static Parser readCommand() throws DukeException {
        String input = userInput.getText();
        Label userText = new Label(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user))
        );
        userInput.clear();
        return new Parser(input);
    }

    /**
     * Prints out multiple Strings onto a single dialog box (Duke).
     * @param outputs Array containing all the strings to be printed out in a single block.
     */
    public static void printBlock(String[] outputs) {
        String completeOutput = "";
        for (String output : outputs) {
            completeOutput = completeOutput + output + "\n";
        }
        Label dukeText = new Label(completeOutput);
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

    }

    /**
     * Prints out a single line of String onto a dialog box (Duke).
     * @param output String to be printed out in a block.
     */
    public static void printBlock(String output) {
        Label dukeText = new Label(output);
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }
}

