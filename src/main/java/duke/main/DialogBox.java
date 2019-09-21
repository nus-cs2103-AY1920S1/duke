package duke.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog; //The dialog text.
    @FXML
    private Circle circle; //The circular image.

    /**
     * Initiates the DialogBox object.
     *
     * @param text The text in the dialog.
     * @param img The image of the responder.
     */
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

        circle.setStroke(Color.SEASHELL);
        circle.setFill(new ImagePattern(img));
        circle.setEffect(new DropShadow(+30d, 0d, +2d, Color.DARKGRAY));
//        displayPicture.setImage(img);
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
     * Initiates user dialog box.
     *
     * @param text The user's command.
     * @param img The user's picture.
     * @return
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color:snow; -fx-background-radius: 10 10 10 10;");
        return db;
    }

    /**
     * Initiates duke robot's dialog box.
     *
     * @param text The duke robot's response.
     * @param img The duke robots's image.
     * @return
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle("-fx-background-color:silver; -fx-background-radius: 10 10 10 10;");
        return db;
    }
}