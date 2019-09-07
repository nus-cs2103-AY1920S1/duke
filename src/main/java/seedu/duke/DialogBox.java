package seedu.duke;

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
import javafx.scene.shape.Circle;

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
     * Constructor for the DialogBox class.
     *
     * @param text the text that the dialog box should contain
     * @param img the image that represents the dialog box
     */
    DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(35, 45, 40));
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
     * Returns a DialogBox containing an image representing User and text containing input from the user.
     *
     * @param text a string containing input from the user
     * @param img an image representing User
     * @return the DialogBox for User
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert !text.equals("") : "Text of user cannot be empty";
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox containing an image representing Duke and text containing response from Duke.
     *
     * @param text a string containing response from Duke
     * @param img an image representing Duke
     * @return the DialogBox for Duke
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        assert !text.equals("") : "Text of duke cannot be empty";
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

}
