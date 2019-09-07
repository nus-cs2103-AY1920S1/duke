package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.rgb;

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
     * Creates an instance of DialogBox as a text box representing the user's input.
     * @param text a String indicating the message from the user
     * @param img an Image instance of the user display picture
     * @return an instance of DialogBox representing the user's input
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userBox = new DialogBox(text, img);
        userBox.dialog.setBackground(new Background(new BackgroundFill(
                Color.rgb(43,82,120),
                new CornerRadii(3),
                Insets.EMPTY
        )));
        userBox.dialog.setTextFill(Color.WHITE);
        return userBox;
    }

    /**
     * Creates an instance of DialogBox as a text box representing Duke's response.
     * @param text a String indicating the response from Duke
     * @param img an Image instance of Duke's display picture
     * @return an instance of DialogBox representing Duke's response
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setBackground(new Background(new BackgroundFill(
                Color.rgb(24,33,42),
                new CornerRadii(3),
                Insets.EMPTY
        )));
        db.dialog.setTextFill(Color.WHITE);
        return db;
    }
}