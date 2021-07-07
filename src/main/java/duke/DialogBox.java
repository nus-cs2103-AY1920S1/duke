package duke;

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
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

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
        displayPicture.setClip(new Circle(50, 50, 50));
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

    private static Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/user.png"));
    private static Image dukeImage = new Image(DialogBox.class.getResourceAsStream("/images/duke.png"));

    /**
     * Creates a DialogBox with the specified text and image.
     * @param text text specified by user.
     * @return DialogBox to be rendered in GUI.
     */
    public static DialogBox getUserDialog(String text) {
        return getStandardDialog(text, userImage);
    }

    /**
     * Creates a DialogBox with the specified text and image.
     * @param text text returned by Duke.
     * @return DialogBox to be rendered in GUI.
     */
    public static DialogBox getDukeDialog(String text) {
        var db = getStandardDialog(text, dukeImage);
        db.flip();
        return db;
    }

    private static DialogBox getStandardDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setWrapText(true);
        db.dialog.setMinHeight(Region.USE_PREF_SIZE);
        return db;
    }
}