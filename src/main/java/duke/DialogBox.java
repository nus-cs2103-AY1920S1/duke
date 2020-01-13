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
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

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
        this.dialog.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
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
     * Sets the color of the dialog from the user.
     *
     * @param text User input.
     * @param img Image that is linked to the user.
     * @return Returns a DialogBox so that the user can use it to
     *         interact with the bot.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox box = new DialogBox(text, img);
        box.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgb(255, 218, 26);"
                + "-fx-text-fill: black";
        box.dialog.setStyle(style);
        return box;
    }

    /**
     * Flips the dialog for the bot and colors the bot.
     *
     * @param text Output from the bot.
     * @param img Duke image link to the bot.
     * @return Returns a DialogBox so that the user can interact with it
     *         and see the dialog pop up.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        String style = "-fx-background-color: rgb(255, 218, 26);"
                + "-fx-text-fill: black";
        db.dialog.setStyle(style);
        return db;
    }
}