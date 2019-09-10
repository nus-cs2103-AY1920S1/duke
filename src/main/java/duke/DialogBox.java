package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     * @param text String to be displayed in DialogBox
     * @param img Image to be displayed in DialogBox
     * @param isDuke boolean representing whether the DialogBox is for Duke or for the User
     */
    private DialogBox(String text, Image img, boolean isDuke) {
        try {
            String fxmlResourcePath;
            if (isDuke) {
                fxmlResourcePath = "/view/DukeDialogBox.fxml";
            } else {
                fxmlResourcePath = "/view/UserDialogBox.fxml";
            }
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlResourcePath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(img);
        this.setHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox object customised for Users.
     * @param text String to be displayed in DialogBox
     * @param img Image to be displayed in DialogBox
     * @return DialogBox object customised for Users
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Returns a DialogBox object customised for Duke.
     * @param text String to be displayed in DialogBox
     * @param img Image to be displayed in DialogBox
     * @return DialogBox object customised for Duke
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}