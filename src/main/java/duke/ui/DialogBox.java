package duke.ui;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 * @author Lim Yong Shen, Kevin
 */
public class DialogBox extends HBox {

    private static final String DUKE_BACKGROUND_COLOUR = "-fx-background-color: #a07855";
    private static final String USER_BACKGROUND_COLOUR = "-fx-background-color: #d4b996";

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
        displayPicture.setImage(img);
        dialog.setText(text);
        dialog.setMinHeight(Label.USE_PREF_SIZE);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Returns A user dialog box based on the specified text and image.
     * @param text The specified text.
     * @param img The specified image.
     * @return A user dialog box based on the specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialogBox = new DialogBox(text, img);
        userDialogBox.setStyle(USER_BACKGROUND_COLOUR);
        return userDialogBox;
    }

    /**
     * Returns A Duke dialog box based on the specified text and image.
     * @param text The specified text.
     * @param img The specified image.
     * @return A Duke dialog box based on the specified text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dukeDialogBox = new DialogBox(text, img);
        dukeDialogBox.flip();
        dukeDialogBox.setStyle(DUKE_BACKGROUND_COLOUR);
        return dukeDialogBox;
    }
}
