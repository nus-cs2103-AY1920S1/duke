package seedu.duke;

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
import javafx.scene.layout.*;
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

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(seedu.duke.MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setPadding(new Insets(10));
        dialog.setMinHeight(Region.USE_PREF_SIZE);
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
     * Returns a dialogbox of the user.
     * @param text the user text in the dialogbox
     * @param img the avatar for the user
     * @return Dialogbox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setMinHeight(db.dialog.getMinHeight());
        BackgroundFill bFill = new BackgroundFill(
                Color.web("#C5D1D8"),
                new CornerRadii(5),
                new Insets(5)
        );
        Background background = new Background(bFill);
        db.setBackground(background);
        return db;
    }

    /**
     * Returns a dialogbox of Duke.
     * @param text the Duke response in the dialogbox
     * @param img the avatar for Duke
     * @return Dialogbox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setMinHeight(db.dialog.getMinHeight());
        BackgroundFill bFill = new BackgroundFill(
                Color.web("#B7C6CE"),
                new CornerRadii(5),
                new Insets(5)
        );
        Background background = new Background(bFill);
        db.setBackground(background);
        db.flip();
        return db;
    }
}