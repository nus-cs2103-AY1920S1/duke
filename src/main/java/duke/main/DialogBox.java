package duke.main;

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

import java.io.IOException;
import java.util.Collections;

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
        displayPicture.setClip(new Circle(50.0,50.0,50.0));
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
     * Returns the user DialogBox which will have the user input and user's profile picture.
     *
     * @param l The text written by user.
     * @param iv The profile picture of user.
     * @return DialogBox of user's input and user's profile picture.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns the duke DialogBox which will have the duke response and duke's profile picture.
     *
     * @param l The response of Duke.
     * @param iv The profile picture of duke.
     * @return DialogBox of duke's response and duke's profile picture.
     */
    public static DialogBox getDukeDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}