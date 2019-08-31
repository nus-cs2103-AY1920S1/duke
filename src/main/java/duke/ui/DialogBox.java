package duke.ui;

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
 * FXML abstraction of a dialogBox.
 * Consists of an ImageView to represent the user or duke's image and a label
 * containing response from duke.
 */
class DialogBox extends HBox {
    /** JavaFX Label control containing the dialog message. */
    @FXML
    private Label dialog;
    /** JavaFX ImageView control for displaying the user or duke's image. */
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor of dialog box for initialising the Label and
     * ImageView controls.
     *
     * @param text String dialog message for the Label control.
     * @param image JavaFX Image for the ImageView control.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box, such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Utility method for creating a dialog from the user.
     *
     * @param text String dialog message from the user.
     * @param image JavaFX image of user for the ImageView control.
     * @return User DialogBox instance.
     */
    static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Utility method for creating a dialog from duke.
     *
     * @param text String dialog message from duke.
     * @param image JavaFX image of duke for the ImageView control.
     * @return Duke DialogBox instance.
     */
    static DialogBox getDukeDialog(String text, Image image) {
        var db = new DialogBox(text, image);
        db.flip();
        return db;
    }
}