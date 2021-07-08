package duke.gui.controllers;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    static final String NAME_USER = "User";
    static final String NAME_DUKE = "Duke";

    @FXML
    private Label dialog;
    @FXML
    private Label usernameLabel;
    @FXML
    private Circle displayPictureCircle;

    private DialogBox(String text, Image img, String username) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        usernameLabel.setText(username);
        ImagePattern pattern = new ImagePattern(img);
        displayPictureCircle.setFill(pattern);
        //displayPicture.setImage(img);

        //Fix for cropped text
        dialog.setMinSize(Label.USE_COMPUTED_SIZE, Label.USE_PREF_SIZE);
        this.setMinHeight(dialog.getMinHeight());
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        setStyle("-fx-background-color: pink;");
    }

    /**
     * Return user dialog (GUI) object
     * .
     * @param text text to display
     * @param img user image to display
     * @return DialogBox user dialog
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, NAME_USER);
    }

    /**
     * Return duke dialog (GUI) object but flipped.
     *
     * @param text text to display
     * @param img duke image to display
     * @return DialogBox duke dialog
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, NAME_DUKE);
        db.flip();
        return db;
    }
}