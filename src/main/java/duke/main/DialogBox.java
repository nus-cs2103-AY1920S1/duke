package duke.main;

import java.io.IOException;
import java.util.Collections;

import duke.controllers.MainWindow;
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

    public static DialogBox getUserDialog(String text, Image img) {
        assert img!=null : "Image should be valid";
        assert text!=null : "Text shouldn't be empty";
        DialogBox current = new DialogBox(text, img);
        current.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(28, 97, 176, 1);"
                + "-fx-text-fill: white";
        current.dialog.setStyle(style);
        return current;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        assert img != null : "Image should be valid";
        assert text != null : "Text shouldn't be empty";
        var db = new DialogBox(text, img);
        db.flip();
        String style = "-fx-background-color: rgba(203, 207, 215, 1);";
        db.dialog.setStyle(style);
        return db;
    }
}