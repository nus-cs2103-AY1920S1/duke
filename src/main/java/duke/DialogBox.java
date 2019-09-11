package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
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
        displayPicture.setClip(new Circle(50, 50, 40));
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
     * Gets User Dialogue.
     *
     * @param text The text to pass in.
     * @param img  The image of User.
     * @return The User Dialog Box.
     */
    static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(119, 136, 153, 0.5);";
        db.setStyle(style);
        return db;
    }

    /**
     * Gets Duke Dialogue.
     * @param text The text to pass in.
     * @param img The image of Duke.
     * @return The User Dialog Box.
     */
    static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(211, 211, 211, 0.5);";
        db.setStyle(style);
        return db;
    }
}