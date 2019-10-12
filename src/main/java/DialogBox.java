import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Represents the UI dialog box.
     * @param text refers to the text which is going to be displayed
     * @param img refers to the image of the user sending the text
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            HBox.setHgrow(dialog, Priority.ALWAYS); // box will resize according to text
            HBox.setHgrow(displayPicture, Priority.ALWAYS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
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
     * Returns the DialogBox containing the image and text of the user.
     * @param text refers to the text the user sends
     * @param img refers to user's image
     * @return DialogBox containing the image and text of the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns the DialogBox containing the image and text of DukeBot.
     * @param text refers to the text DukeBot sends
     * @param img refers to DukeBot's image
     * @return DialogBox containing the image and text of DukeBot
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

