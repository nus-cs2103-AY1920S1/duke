package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
     * Constuctor.
     *
     * @param text String
     * @param img Image
     * @param isDuke boolean
     */
    private DialogBox(String text, Image img, boolean isDukeReply) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (!isDukeReply) {
            dialog.setTextAlignment(TextAlignment.RIGHT);
        }
        dialog.setWrappingWidth(280.0);
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
     * Gets the user dialog.
     *
     * @param text String
     * @param img Image
     * @return DialogBox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Gets the duke dialog.
     *
     * @param text String
     * @param img Image
     * @return DialogBox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        db.setStyle("-fx-background-color: grey;");
        return db;
    }
}