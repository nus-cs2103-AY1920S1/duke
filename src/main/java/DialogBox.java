import static jermi.misc.Constant.DIALOG_BOX_FXML_PATH;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    /** Dialog to be displayed. */
    @FXML
    private Label dialog;
    /** Image to be displayed. */
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor for class.
     * Contains the dialog and image to be displayed.
     *
     * @param text Dialog to be displayed.
     * @param image Image to be displayed.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(DIALOG_BOX_FXML_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box such that the displayed image is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a {@link DialogBox} containing the user's image and the dialog to be displayed.
     *
     * @param text Dialog to be displayed.
     * @param image User's image to be displayed.
     * @return Dialog box containing the user's image and the dialog to be displayed.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Returns a {@link DialogBox} containing Jermi's image and the dialog to be displayed.
     *
     * @param text Dialog to be displayed.
     * @param image Jermi's image to be displayed.
     * @return Dialog box containing Jermi's image and the dialog to be displayed.
     */
    public static DialogBox getJermiDialog(String text, Image image) {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        return dialogBox;
    }
}