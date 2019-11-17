package duke.view;

import duke.main.Duke;
import java.io.IOException;
import java.util.Collections;

import duke.ui.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This is the dialog box controller in duke.
 * This control represents a dialog box consisting of a display picture to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    /**
     * This is the user icon in duke.
     */
    private static Image USER_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/user.png"));

    /**
     * This is the system icon in duke.
     */
    private static Image DUKE_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/duke.png"));

    /**
     * This is the duke program to get the system output with user input.
     */
    private static Duke DUKE = new Duke();
    /**
     * This is the text component of the system output / user input.
     */
    @FXML
    private Text dialog;
    /**
     * This is the display picture for user / system icons.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * Construct a new dialog box with its corresponding text message and image with display picture on the right and
     * text on the left.
     * @param text the text to display in the dialog box
     * @param img the image to display in the display picture
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setId("dialogText");
    }

    /**
     * Create a new user dialog box with the input message.
     * @param inputMessage the message input by the user
     * @return a dialog box with the default user icon
     */
    private static DialogBox userDialogBox(String inputMessage) {
        return new DialogBox(inputMessage, USER_IMAGE);
    }

    /**
     * Create a new duke dialog box with the output message.
     * @param outputMessage the mesage output by the duke program
     * @return a dialog box with the default system icon
     */
    private static DialogBox dukeDialogBox(String outputMessage) {
        return new DialogBox(outputMessage, DUKE_IMAGE).flip();
    }

    /**
     * Flips the dialog box such that the display picture is on the left and text on the right.
     * @return a flipped dialog box
     */
    private DialogBox flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
        this.flipDialogTextAlignment();
        return this;
    }

    /**
     * Flips the text alignment in the dialog box. If original text alignment is positioned left, text will be aligned
     * right. Otherwise, text will be aligned left.
     */
    public void flipDialogTextAlignment() {
        if (dialog.getTextAlignment() == TextAlignment.LEFT) {
            dialog.setTextAlignment(TextAlignment.RIGHT);
        } else {
            dialog.setTextAlignment(TextAlignment.LEFT);
        }
    }

    /**
     * Gets the user dialog box from the input message.
     * @param inputMessage the input message by the user
     * @return the user dialog box
     */
    public static DialogBox getUserDialog(String inputMessage) {
        return userDialogBox(inputMessage);
    }

    /**
     * Gets the duke dialog box from the input message.
     * @param inputMessage the input message by the user
     * @return the duke dialog box
     */
    public static DialogBox getDukeDialog(String inputMessage) {
        String outputMessage = DUKE.runWithUserInput(inputMessage);
        return dukeDialogBox(outputMessage);
    }

    /**
     * Gets the welcome dialog box during startup to be displayed.
     * @return the welcome dialog box
     */
    public static DialogBox showWelcome() {
        return dukeDialogBox(new Ui().showWelcome());
    }

}
