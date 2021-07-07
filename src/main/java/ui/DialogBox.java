package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Custom control for GUI encapsulating a dialog box that contains two
 * other controls - ImageView and Label.
 *
 * <p> The code for DialogBox has been reused from the JavaFX tutorials
 * on the duke repository written by the CS2103 teaching team. </p>
 *
 * @authors: j-lum
 * @version: CS2103 AY19/20 Sem 1 iP Week 4
 */
public class DialogBox extends HBox {

    /** The text to be displayed on the GUI. */
    private Label text;

    /** The display picture accompanying the text to be displayed. */
    private ImageView displayPicture;

    /**
     * Initialises a dialog box with a given label and image.
     * @param label the text associated with this dialog box.
     * @param imageView the display image associated with this dialog box.
     */
    public DialogBox(Label label, ImageView imageView) {
        this.text = label;
        this.displayPicture = imageView;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns User's dialog box.
     * @param label the text associated with this dialog box.
     * @param imageView the display image associated with this dialog box.
     * @return the user's dialog box.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Returns Duke's dialog box.
     * @param label the text associated with this dialog box.
     * @param imageView the display image associated with this dialog box.
     * @return duke's dialog box.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        var db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }
}