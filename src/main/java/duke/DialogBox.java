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
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

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
    @FXML
    private final Circle displayPictureFrame = new Circle(45, 46, 45, Color.BLACK);

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
        displayPicture.setClip(displayPictureFrame);
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
     * Generates user's dialog from input.
     *
     * @param text User's input
     * @param img User's profile picture
     * @return DialogBox User's dialog in GUI
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var dialogUser = new DialogBox(text, img);
        dialogUser.setId("user");
        return dialogUser;
    }

    /**
     * Generates Duke's response to user's input.
     *
     * @param text Duke's response
     * @param img Duke's profile picture
     * @return DialogBox Duke's dialog in GUI
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dialogDuke = new DialogBox(text, img);
        dialogDuke.flip();
        dialogDuke.setId("duke");
        return dialogDuke;
    }
}
