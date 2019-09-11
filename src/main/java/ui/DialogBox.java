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

    private DialogBox(String text, Image img, String color, Paint textFill) {
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
        dialog.setStyle("-fx-background-color:" + color + "; -fx-background-radius: 20");
        getCircleImg(img, displayPicture);
//        displayPicture.setImage(img);
//        // Set the height and width of the pictures
//        displayPicture.setFitWidth(80.0);
//        displayPicture.setFitHeight(80.0);
//        // Make the user's and Duke's profile pictures appear as circles
//        displayPicture.setClip(new Circle(40.0, 40.0, 40.0, Paint.valueOf("white")));
    }

    /**
     * Returns a DialogBox representing the user input.
     *
     * @param text the user input.
     * @param img user's profile image.
     * @return DialogBox for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "#94EFD1", Color.BLACK);
    }

    /**
     * Returns a DialogBox representing Duke's response.
     *
     * @param text Duke's response.
     * @param img Duke's profile image.
     * @return DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "#216168", Color.WHITE);
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