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
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label name;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param text Message to be displayed
     * @param img Image to be displayed
     * @param isDuke True if the sender of the message is Duke
     */
    private DialogBox(String text, Image img, boolean isDuke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        customiseText(isDuke, text);
        formatDisplayPicture(img);
    }

    private void customiseText(boolean isDuke, String text) {
        name.setText(isDuke ? "Perry" : "You");
        dialog.setText(text);
    }

    private void formatDisplayPicture(Image img) {
        Circle clip = new Circle(30, 30, 30);
        displayPicture.setClip(clip);
        displayPicture.setFitWidth(60.0);
        displayPicture.setFitHeight(60.0);
        displayPicture.setImage(img);
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
     * Returns a user dialog object.
     *
     * @param text Message to be displayed
     * @param img Image to be displayed
     * @return a new DialogBox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Returns a duke dialog object.
     *
     * @param text Message to be displayed
     * @param img Image to be displayed
     * @return a new DialogBox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}

