package ui;

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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    /**
     * Constructor of the DialogBox that takes in
     * the text for the Label, a Paint value specifying the text colour,
     * a String value specifying the background colour of the Label, and an Image.
     *
     * @param text the text of the Label.
     * @param textFill a Paint value that determines the text fill.
     * @param backgroundColor the background color of the Label.
     * @param img the user's or Duke's profile picture.
     */
    private DialogBox(String text, Paint textFill, String backgroundColor, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setTextFill(textFill);
        dialog.setStyle("-fx-background-color:" + backgroundColor + "; -fx-background-radius: 20");
        getCircleImg(img, displayPicture);
    }

    /**
     * Returns a DialogBox representing the User input.
     *
     * @param text the user input.
     * @param img user's profile image.
     * @return DialogBox for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, Color.BLACK, "white", img);
    }

    /**
     * Returns a DialogBox representing Duke's response.
     *
     * @param text Duke's response.
     * @param img Duke's profile image.
     * @return DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, Color.WHITE, "#216168", img);
        db.flip();
        return db;
    }

    /**
     * Returns a DialogBox representing Duke's response when an error has occurred.
     *
     * @param text Duke's response.
     * @param img Duke's profile image.
     * @return DialogBox for Duke.
     */
    public static DialogBox getDukeErrorDialog(String text, Image img) {
        var db = new DialogBox(text, Color.WHITE, "#593984", img);
        db.flip();
        return db;
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
     * Obtain a circular image by filling a circle with image.
     *
     * @param img image to fill the circle.
     * @param circle circle which will be filled by the image.
     */
    public void getCircleImg(Image img, Circle circle) {
        circle.setStroke(Color.TEAL);
        circle.setFill(new ImagePattern(img));
    }
}